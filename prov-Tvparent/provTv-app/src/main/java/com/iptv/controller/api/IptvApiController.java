package com.iptv.controller.api;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.iptv.controller.util.AesUtils;
import com.iptv.dao.insertDto.ReceiveTotalServerTvInformationParam;
import com.iptv.model.tv.entity.Tv;
import com.iptv.model.tv.helper.TvArea;
import com.iptv.model.tv.helper.TvBase;
import com.iptv.util.DateUtils;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iptv.controller.dto.ActivationParam;
import com.iptv.controller.util.ServiceCode;
import com.iptv.service.tv.TvAreaService;
import com.iptv.service.tv.TvBaseService;
import com.iptv.service.tv.TvService;
import com.iptv.util.BaseCode;
import com.iptv.util.BaseResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RestController
public class IptvApiController {
	private static final Logger log = LoggerFactory.getLogger(IptvApiController.class);
	
	@Autowired
	private TvService tvService;
	
	@Autowired
	private TvAreaService  tvAreaService;
	
	@Autowired
	private TvBaseService tvBaseService;
	
	 /**
     * 接收电视信息
     * @param
     * @return
     */
    @RequestMapping(value = "tv_msg.do", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String receiveTvPrograms(@RequestBody String tvs,HttpServletRequest request, HttpServletResponse response) {
    	
    	JSONArray list = JSONObject.fromObject(tvs).getJSONArray("data");
    	String data ="";
    	try{
    		synchronized(this){
    			
    			if (null == list && list.size() < 1) {
    				data = ServiceCode.err(1005,"发送的电视列表空");
                } else {
                	for(Object obj :list){
                		JSONObject jsonObject =   JSONObject.fromObject(obj);
                		Tv tv = (Tv) JSONObject.toBean(jsonObject,Tv.class);
                		tvService.insert(tv);
                	}
                	data = ServiceCode.succ("发送的电视信息已经入库成功");
                }
    		}
    		return data;
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		data = ServiceCode.parameterErr();
    		return data;
    	}
    }
    
    @RequestMapping(value="testtest.do",method=RequestMethod.GET)
    @ResponseBody
    private String aaa(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("*********************************");
		return "1234567";
    	
    }
  
    /**
     * 根据电视mac地址和省份编码查看电视是否在本省份
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getTvExists.do", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseResponse getTvExists(/*@Valid final TvSearchParam tvSearchParam, BindingResult bindingResult, */HttpServletRequest request, HttpServletResponse response){
    		
    	/*try{
			if(bindingResult.hasErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                FieldError fieldError = fieldErrors.get(0);
                return BaseResponse.failedCustom(-1, fieldError.getDefaultMessage()).build();
            }
            log.debug("[getTvExists.do] REQUEST:{}",tvSearchParam.toString());
		}catch(Exception e){
			e.printStackTrace();
			return BaseResponse.failedCustom(-1,"服务异常").build();
		}
    	 */
    	final String body = request.getParameter("params");
		final String[] param;
		try {
			log.info("进入据电视mac地址和省份编码查看电视是否在本省份接口");
			param   = AesUtils.decrypt(body).split("&");
		} catch (Exception e) {
			return BaseResponse.failedCustom(-1,"查询电视是否在本省市失败，原因是接收参数异常").build();
		}

		final String mac = StringUtils.equals(param[0].split("=")[0],"mac")?param[0].split("=")[1]:"";  //电视mac地址
    	final int provId =  StringUtils.equals(param[1].split("=")[0],"provId")?Integer.parseInt(param[1].split("=")[1]):0 ; //电视所在省份
		log.info("mac地址是{}的电视正在查询是否鉴权",mac);
    	//根据电视mac地址 和 电视所在省份 查询当前电视是否在本省份注册过
    	final TvArea tvAreaOptional = this.tvAreaService.getTvAreaByMacAndProvince(mac, provId);
    	if(null != tvAreaOptional){
    		//查询电视基本信息
    		final TvBase tvBase = this.tvBaseService.getTvBaseByMac(mac);

			TvBase tvBase1 = this.tvBaseService.selectByPrimaryKey(tvBase.getId());
			ActivationParam  activationParamBuilder = getActivationType(tvBase1);
			String code = activationParamBuilder.getCode();
			String message = activationParamBuilder.getMessage();
			if(StringUtils.equals("0",code)){
				return BaseResponse.successCustom("当前电视在本省份存在，同时电视未激活").addParam(String.valueOf(BaseCode.getCode(provId, "AREA_HAVE_THIS_TV_NO_ACTIVATION")), message).build();
			}else if(StringUtils.equals("1",code)){
				return BaseResponse.successCustom("当前电视在本省份存在，同时电视已经激活").addParam(String.valueOf(BaseCode.getCode(provId, "AREA_HAVE_THIS_TV_HAVE_ACTIVATION")), "当前电视在本省份存在，同时电视已经激活").build();
			}
			return BaseResponse.successCustom("当前电视在本省份存在，但是没有查询到电视基本信息").addParam(String.valueOf(BaseCode.getCode(provId, "AREA_HAVE_THIS_TV_NO_TVMESSAGE")), "当前电视在本省份存在，但是没有查询到电视基本信息").build();
		}else{
    		return BaseResponse.failedCustom(BaseCode.getCode(provId, "AREA_NOT_HAVE_THIS_TV"), "当前电视在本省份不存在").build();
    	}
    }
    
    /**
     * 根據mac地址查詢電視基本信息接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getTvMessageByMac.do",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseResponse getTvMessageByMac(/*@RequestParam("mac") String mac, */HttpServletRequest request, HttpServletResponse response){

		final String body = request.getParameter("params");
		final String[] param;
		try {
			param   = AesUtils.decrypt(body).split("&");
		} catch (Exception e) {
			return BaseResponse.failedCustom(BaseCode.getCode(000, "PARAM_CODE_OF_MAC"), "查詢所需的mac空").build();
		}

		final String mac = StringUtils.equals(param[0].split("=")[0],"mac")?param[0].split("=")[1]:"";  //电视mac地址
		log.info("mac地址是{}的电视正在调用getTvMessageByMac接口查询基本信息",mac);
    	final TvBase tvBaseOptional = this.tvBaseService.getTvBaseByMac(mac);
    	if(null!= tvBaseOptional){
			TvBase tvBase = this.tvBaseService.selectByPrimaryKey(tvBaseOptional.getId());
    		return BaseResponse.successCustom("查询成功").setObj(tvBase).addParam(String.valueOf(BaseCode.SUCCESS_CODE), "查询成功").build();
    	}else{
    		return BaseResponse.failedCustom(BaseCode.FIND_TV_BY_CODE_ERROR, "根据mac地址查询电视失败").build();
    	}
    }
    
    /**
     * 更新電視基本信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="updateTvMessage.do",method={RequestMethod.POST,RequestMethod.GET})
    public BaseResponse updateTvMessage(/*@Valid  TvInsertParam tvInsertParam,BindingResult bindingResult,*/ HttpServletRequest request, HttpServletResponse response){
    	/*try{
			if(bindingResult.hasErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                FieldError fieldError = fieldErrors.get(0);
                return BaseResponse.failedCustom(-1, fieldError.getDefaultMessage()).build();
            }
            log.debug("[updateTvMessage.do] REQUEST:{}",tvInsertParam.toString());
		}catch(Exception e){
			e.printStackTrace();
			return BaseResponse.failedCustom(-1,"服务异常").build();
		}*/
		final String body = request.getParameter("params");
		final String[] param;
		try {
			param   = AesUtils.decrypt(body).split("&");
		} catch (Exception e) {
			return BaseResponse.failedCustom(BaseCode.getCode(000, "PARAM_CODE_OF_MAC"), "查询所需的mac空").build();
		}
		Map<String,String> map = new HashMap<String,String>();
		int i=0;
		for(String str: param){
			map.put(param[i].split("=")[0],param[i].split("=")[1]);
			i++;
		}
    	try{
	    	final String mac = map.get("mac");
	    	//Optional<TvBase> tv = this.tvBaseService.getTvBaseByMac(mac);
			TvBase tvBase = this.tvBaseService.getTvBaseByMac(mac);
	    	if(null!= tvBase){
	    		tvBase.setUserName(map.get("userName"));
	    		tvBase.setPassword(map.get("password"));
	    		tvBase.setSoundServerAddress(map.get("soundServerAddress"));
	    		tvBase.setUpgradeServerAddress(map.get("upgradeServerAddress"));
	    		tvBase.setPlatformType(Byte.parseByte(map.get("platformType")));
	    		tvBase.setActivationType(gettype(tvBase));
				tvBase.setUpdateTime(DateUtils.getNowDateyMDdHms());
	    		this.tvBaseService.updateByPrimaryKeySelective(tvBase);
	    		log.info("mac地址是{}的电视更新信息成功",mac);
				return BaseResponse.successCustom().addParam(String.valueOf(BaseCode.SUCCESS_CODE), "更新电视基本信息成功").build();
	    	}else{
				return BaseResponse.failedCustom(-1,"更新电视基本信息成功失败").addParam(String.valueOf(BaseCode.SUCCESS_CODE), "根据mac地址查找不到电视信息，不进行更新").build();
			}

    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return BaseResponse.failedCustom(-1,"服务异常").build();
    }


	/**
	 * 接收 总服务器电视信息入库
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="receiveTotalServerTvInformation.do",method={RequestMethod.POST,RequestMethod.GET})
	public BaseResponse receiveTotalServerTvInformation(@Valid ReceiveTotalServerTvInformationParam vInformationParam, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){
    	try{
			if(bindingResult.hasErrors()) {
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                FieldError fieldError = fieldErrors.get(0);
                return BaseResponse.failedCustom(-1, fieldError.getDefaultMessage()).build();
            }
            log.debug("[receiveTotalServerTvInformation.do] REQUEST:{}",vInformationParam.toString());
		}catch(Exception e){
			e.printStackTrace();
			return BaseResponse.failedCustom(-1,"服务异常").build();
		}
		List<TvBase> list = new ArrayList<TvBase>();
		if(null!=vInformationParam.getList() && vInformationParam.getList().size()>0){
			list = vInformationParam.getList();
		}
		try{
			if(null!=list && list.size()>0){
				this.tvBaseService.insertList(list);
				return BaseResponse.successCustom().addParam(String.valueOf(BaseCode.SUCCESS_CODE), "接收 总服务器电视信息入库成功").build();
			}
		}catch(Exception e){
			log.debug("从总服务器接收的电视信息入库失败");
			return BaseResponse.failedCustom(-1,"服务异常").build();
		}
		return BaseResponse.failedCustom(-1,"服务异常").build();
	}
    
    
    
    
    /**
     * 得到激活状态 返回ActivationParam 
     * @param tvBase
     * @return
     */
    public ActivationParam getActivationType(final TvBase tvBase){
    	ActivationParam.ActivationParamBuilder activationParamBuilder = ActivationParam.builder();
    	final List<String> list = new ArrayList<String>();
    	//第一步 先判断激活状态 是否正确 判断依据是 查看

        final String mac = tvBase.getMac();

        final String userName = tvBase.getUserName();
        if(StringUtils.isEmpty(userName)){
        	return activationParamBuilder.code("0").message("未激活_userName_用户名称").build();
        }
        final String password = tvBase.getPassword();
        if(StringUtils.isEmpty(password)){
			return activationParamBuilder.code("0").message("未激活_password_密码").build();
        }
        final String soundServerAddress = tvBase.getSoundServerAddress();
        if(StringUtils.isEmpty(soundServerAddress)){
        	return activationParamBuilder.code("0").message("未激活_soundServerAddress_健全地址").build();
        }
        final String upgradeServerAddress = tvBase.getUpgradeServerAddress();
        if(StringUtils.isEmpty(upgradeServerAddress)){
        	return activationParamBuilder.code("0").message("未激活_upgradeServerAddress_升级服务器地址").build();
        }
        final byte platformType = tvBase.getPlatformType();
        if(0 == platformType){
        	return activationParamBuilder.code("0").message("未激活_platformType_平台类型").build();
        }
        final byte activationType = tvBase.getActivationType();
    	if(activationType ==0){
    		if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password) || StringUtils.isNotEmpty(soundServerAddress)||
    			StringUtils.isNotEmpty(upgradeServerAddress) || platformType !=0){
    			//说明激活状态不对，
    			tvBase.setActivationType((byte)1);
    			this.tvBaseService.updateByPrimaryKey(tvBase);
            	return activationParamBuilder.code("1").message("已激活").build();
    		}
    	}else{
        	return activationParamBuilder.code("1").message("已激活").build();
    	}
    	return activationParamBuilder.code("-1").message("异常").build();
    }
	/**
	 * 得到激活状态 返回ActivationParam
	 * @param tvBase
	 * @return
	 */
	public byte gettype(final TvBase tvBase){
		ActivationParam.ActivationParamBuilder activationParamBuilder = ActivationParam.builder();
		final List<String> list = new ArrayList<String>();
		//第一步 先判断激活状态 是否正确 判断依据是 查看

		final String mac = tvBase.getMac();

		final String userName = tvBase.getUserName();
		if(StringUtils.isEmpty(userName)){
			return (byte)0;
		}
		final String password = tvBase.getPassword();
		if(StringUtils.isEmpty(password)){
			return (byte)0;
		}
		final String soundServerAddress = tvBase.getSoundServerAddress();
		if(StringUtils.isEmpty(soundServerAddress)){
			return (byte)0;
		}
		final String upgradeServerAddress = tvBase.getUpgradeServerAddress();
		if(StringUtils.isEmpty(upgradeServerAddress)){
			return (byte)0;
		}
		final byte platformType = tvBase.getPlatformType();
		if(0 == platformType){
			return (byte)0;
		}
		return (byte)1;
	}
    
}
