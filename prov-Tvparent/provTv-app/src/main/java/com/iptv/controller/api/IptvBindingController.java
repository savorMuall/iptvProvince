package com.iptv.controller.api;

import com.iptv.controller.util.AesUtils;
import com.iptv.model.bindingPlaces.entity.BindingPlaces;
import com.iptv.model.province.entity.Province;
import com.iptv.model.tvMacBinding.entity.TvMacBinding;
import com.iptv.service.bindingPlaces.BindingPlacesService;
import com.iptv.service.province.ProvinceService;
import com.iptv.util.BaseCode;
import com.iptv.util.BaseResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tvMacBinding.TvMacBindingService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangzhan <王占, wangzhan@cis.com.cn>
 * @version v1.0
 * @project prov-app
 * @Description
 * @encoding UTF-8
 * @date 2018/1/4
 * @time 13:55
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@RestController
@RequestMapping("/binding/")
public class IptvBindingController {
    private static final Logger log = LoggerFactory.getLogger(IptvBindingController.class);

    @Resource
    private BindingPlacesService bindingPlacesService;

    @Resource
    private TvMacBindingService tvMacBindingService;

    @Resource
    private ProvinceService provinceService;

    /**
     * 接收mac绑定ip接口
     * @param
     *   params 字符串封装
     *      mac  mac 地址
     *      accountNumber       用户账号
     *      provId              省份id
     *      ipAddress           ip地址
     *      state              （1 暂存 2 保存  3撤销）
     * @return
     */
    @RequestMapping(value = "tv_binding.do", method = {RequestMethod.POST,RequestMethod.GET})
    public BaseResponse receiveTvPrograms(@RequestParam String params,
                                    HttpServletRequest request, HttpServletResponse response) {
log.info("************************************************************************************");
log.info("************************************************************************************");
log.info("**************tv_binding.do*********************************");
log.info("************************************************************************************");
log.info("************************************************************************************");
        // "mac=FCD5D900B901&accountNumber=22030850493&provId=31&ipAddress=210.172.192.19&state=1";

        final String body = request.getParameter("params");
        final String[] param;
        try {
            param = AesUtils.decrypt(body).split("&");
        } catch (Exception e) {
            return BaseResponse.failedCustom(5001, "接收的 mac与ip的绑定参数解析失败").build();
        }
        String mac = "";
        String accountNumber = "";
        Integer provId = null;
        String ipAddress = "";
        Byte state = null;
        if (null != param && param.length > 0) {
            mac = param[0].split("=")[0].equals("mac") ? param[0].split("=")[1] : "";
            accountNumber = param[1].split("=")[0].equals("accountNumber") ? param[1].split("=")[1] : "";
            provId = param[2].split("=")[0].equals("provId") ? Integer.parseInt(param[2].split("=")[1]) : 0;
            ipAddress = param[3].split("=")[0].equals("ipAddress") ? param[3].split("=")[1] : "";
            state = param[4].split("=")[0].equals("state") ? Byte.parseByte(param[4].split("=")[1]) : 0;
        }
        if (StringUtils.isEmpty(mac) || StringUtils.isEmpty(accountNumber) || StringUtils.isEmpty(ipAddress)
                || null == provId) {
            return BaseResponse.failedCustom(5001, "接收mac绑定ip接口 参数不全").build();
        }

        TvMacBinding tvMacBinding = tvMacBindingService.selectByMacIpProvId(mac, accountNumber, provId, (byte) 2);
        TvMacBinding tvMacBindinga = tvMacBindingService.selectByMacIpProvId(mac, accountNumber, provId, (byte) 1);
        BindingPlaces bindingPlaces = bindingPlacesService.selectByProvIdTimedesc(provId);
        Province province = provinceService.selectByProvId(provId);
        if (null == tvMacBinding) {
            //说明没有绑定过
            //查看当前批次下时候还有剩余的名额
            if (null == bindingPlaces) {
                return BaseResponse.failedCustom(5002, "当前" + province.getProvName() + "省份没有分配健权名额").build();
            }
            if (bindingPlaces.getState() == 3) {
                return BaseResponse.failedCustom(5003, "当前" + province.getProvName() + "省份健权名额已经用光").build();
            }
            if (null != tvMacBindingService.selectByMacIpProvId(mac, accountNumber, provId, (byte) 1)) {
                if (state == 1) {
                    return BaseResponse.failedCustom(5004, "当前mac所在省份"+province.getProvName()+"正在进行联通健全，请耐心等待").build();
                }
            }
            synchronized (this) {

                if (state == 2) {
                    //保存
                    tvMacBindinga.setState((byte) 2);
                    tvMacBindingService.updateByPrimaryKeySelective(tvMacBindinga);

                    if (tvMacBindinga.getNumber() == bindingPlaces.getNumber()) {
                        bindingPlaces.setState((byte) 3);
                        bindingPlacesService.updateByPrimaryKey(bindingPlaces);
                    }
                    //判断当前号码是否是其他mac撤回的编码
                    //根据省份id  编号  批次号 state ＝ 1
                    //  TvMacBinding tvMacBinding1 = this.tvMacBindingService.selectByProvIdState()
                    //查询当前批次的编码关联的旧的mac信息
                    TvMacBinding tvMacBinding1 = this.tvMacBindingService.selectOldByProvIdStateNumber(tvMacBindinga.getProvId(),
                            tvMacBindinga.getBatchNumber(), tvMacBindinga.getNumber());
                    if (null != tvMacBinding1) {
                        tvMacBinding1.setIsReusing((byte) 2);  //号码是否被重用 2 是
                        tvMacBinding1.setState((byte) 6);      //6替用生效
                        tvMacBindingService.updateByPrimaryKeySelective(tvMacBinding1);
                    }
                    if (tvMacBindinga.getNumber() == bindingPlaces.getNumber()) {
                        return BaseResponse.successCustom("当前mac健全成功，同时需要注意：当前" + province.getProvName() + "省份已经没有可供使用的健权名额，后续mac无法进行健全，请注意").build();
                    } else {
                        return BaseResponse.successCustom("当前mac健全成功").build();

                    }

                }else if(state == 1){
                    // 查询tv_mac_binding state 为3撤销  4过期 时间生序排序
                    TvMacBinding tvMacBinding1 = tvMacBindingService.selectByProvIdState(provId);
                    if (null != tvMacBinding1) {
                        //本次号码使用这个撤回的号
                        TvMacBinding vo = new TvMacBinding();
                        vo.setMac(mac);
                        vo.setAccountNumber(accountNumber);
                        vo.setBatchNumber(tvMacBinding1.getBatchNumber());
                        vo.setIpAddress(ipAddress);
                        vo.setIsReusing((byte) 1);
                        vo.setNumber(tvMacBinding1.getNumber());
                        vo.setProvId(provId);
                        vo.setState((byte) 1);
                        tvMacBindingService.insertSelective(vo);

                        tvMacBinding1.setState((byte) 5);
                        tvMacBindingService.updateByPrimaryKeySelective(tvMacBinding1);
                        return BaseResponse.successCustom("当前mac所在省份"+province.getProvName()+"使用的编号是mac为" + tvMacBinding1.getMac() + "退还的名额，编号是" + tvMacBinding1.getNumber()).build();
                    } else {
                        //本次使用新分配的号码
                        TvMacBinding tvMacBinding2 = tvMacBindingService.selectByProvIdStateUnUsed(provId);
                        if (tvMacBinding2.getNumber() + 1 > bindingPlaces.getNumber()) {
                            return BaseResponse.failedCustom(5003, "当前" + province.getProvName() + "省份健权名额已经用光").build();
                        } else if (tvMacBinding2.getNumber() + 1 <= bindingPlaces.getNumber()) {
                            //本次号码使用这个撤回的号
                            TvMacBinding vo = new TvMacBinding();
                            vo.setMac(mac);
                            vo.setAccountNumber(accountNumber);
                            vo.setBatchNumber(bindingPlaces.getBatchNumber());
                            vo.setIpAddress(ipAddress);
                            vo.setIsReusing((byte) 1);
                            vo.setNumber(tvMacBinding2.getNumber() + 1);
                            vo.setProvId(provId);
                            vo.setState((byte) 1);
                            tvMacBindingService.insertSelective(vo);
                            if (vo.getNumber() == bindingPlaces.getNumber()) {
                                return BaseResponse.successCustom("当前mac所在省份"+province.getProvName()+"使用的编号是" + province.getProvName() + "省份的最后一个号码" + vo.getNumber() + "，后续别的mac将不再支持健权").build();

                            }
                            return BaseResponse.successCustom("当前mac所在省份"+province.getProvName()+"使用的编号是" + province.getProvName() + "省份的" + vo.getNumber() + "编号").build();
                        }
                    }
                }
            }
        } else {
            return BaseResponse.failedCustom(5005, "当前mac所在省份"+province.getProvName()+"已经健权成功，无需再次绑定").build();
        }
        return BaseResponse.successCustom("").build();

    }

}
