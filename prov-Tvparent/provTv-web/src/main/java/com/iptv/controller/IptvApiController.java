package com.iptv.controller;

import com.iptv.model.tv.entity.Tv;
import com.iptv.model.tv.helper.TvBase;
import com.iptv.service.tv.TvBaseService;
import com.iptv.service.tv.TvService;
import com.iptv.util.AesUtils;
import com.iptv.util.BaseCode;
import com.iptv.util.BaseResponse;
import com.iptv.util.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

@Slf4j
@Controller
public class IptvApiController {
	
	@Autowired
	private TvService tvService;

	@Autowired
	private TvBaseService tvBaseService;

	/**
	 * 接收电视信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/api/tv_msg.do", method = { RequestMethod.POST })
	@ResponseBody
	public String receiveTvPrograms(@RequestBody String tvs, HttpServletRequest request, HttpServletResponse response) {

		JSONArray list = JSONObject.fromObject(tvs).getJSONArray("data");
		String data = "";
		try {
			synchronized (this) {

				if (null == list && list.size() < 1) {
					data = ServiceCode.err(1005, "发送的电视列表空");
				} else {
					for (Object obj : list) {
						JSONObject jsonObject = JSONObject.fromObject(obj);
						Tv tv = (Tv) JSONObject.toBean(jsonObject, Tv.class);
						tvService.insert(tv);
					}
					data = ServiceCode.succ("发送的电视信息已经入库成功");
				}
			}
			return data;
		} catch (Exception e) {
			log.info("{}获取电视信息入库失败",123);
			data = ServiceCode.parameterErr();
			return data;
		}
	}

	/**
	 * 发送电视信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/api/{mac}/getTvMsg.do", method = {
			RequestMethod.GET }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getTvCommands(@PathVariable("mac") String mac, HttpServletRequest request,
                                HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String data = "";
		try {
			if (StringUtils.isEmpty(mac)) {
				data = ServiceCode.err(1005, "发送的mac地址空");
			} else {
				synchronized (this) {
					
					Tv tv = new Tv();
					tv.setMac(mac);
					List<Tv> tvs = new ArrayList<Tv>();
					tvs = this.tvService.findAll(tv);
					if(null!= tvs){
						Tv tv1 = tvs.get(0);
						String json = "{\"deviceName\":\""+tv1.getDeviceName()+"\",\"mac\":\""+tv1.getMac()+"\",\"provName\":\""+tv1.getProvName()+"\",\"upgradeServerAddress\":\""+tv1.getUpgradeServerAddress()+"\",\"soundServerAddress\":\""+tv1.getSoundServerAddress()+"\"}";
						data = ServiceCode.succ(json);
					}
				}
			}
			return data;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			data = ServiceCode.requestTimeOut();
			return data;
		}
	}

	/**
	 * 获取到总服务器发送的省份电视信息
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/api/{mac}/getTotalServerTvMsg.do", method = { RequestMethod.GET, RequestMethod.POST })
	public BaseResponse getTotalServerTvMsg(HttpServletRequest request, HttpServletResponse response) {

		final String body = request.getParameter("params");
		String key = "iptv21\\/22";
		try {
			String tvJson = AesUtils.deCrypt(AesUtils.parseHexStr2Byte(body),key);
			JSONObject jsonobj = JSONObject.fromObject(tvJson);
			JSONArray json = jsonobj.getJSONArray("tv");
			for (Object jsono : json) {
				JSONObject tvBase =  JSONObject.fromObject(jsono);
				TvBase tvBase11 = new TvBase();
			//	tvBase11.setId(Integer.parseInt(String.valueOf(tvBase.get("id"))));
				tvBase11.setMac(String.valueOf(tvBase.get("mac")));
				tvBase11.setUserName(String.valueOf(tvBase.get("userName")));
				tvBase11.setPassword(String.valueOf(tvBase.get("password")));
				tvBase11.setSoundServerAddress(String.valueOf(tvBase.get("soundServerAddress")));
				tvBase11.setUpgradeServerAddress(String.valueOf(tvBase.get("upgradeServerAddress")));
				tvBase11.setPlatformType(Byte.parseByte(String.valueOf(tvBase.get("platformType"))));
				//根据mac地址获取是否有数据 如果有数据，那么更新没有就插入
				TvBase tvBase1 = this.tvBaseService.getTvBaseByMac(tvBase11.getMac());
				if(null !=tvBase1){
					tvBase1.setPlatformType(tvBase11.getPlatformType());
					tvBase1.setSoundServerAddress(tvBase11.getSoundServerAddress());
					tvBase1.setActivationType(tvBase11.getActivationType());
					tvBase1.setUpgradeServerAddress(tvBase11.getUpgradeServerAddress());
					tvBase1.setUserName(tvBase11.getUserName());
					tvBase1.setPassword(tvBase11.getPassword());
					tvBase1.setUpdateTime(DateTime.now().toDate());
					this.tvBaseService.updateByPrimaryKey(tvBase1);
				}else{
					tvBase11.setCreateTime(DateTime.now().toDate());
					this.tvBaseService.insert(tvBase11);
				}
			}
		} catch (Exception e) {
			return BaseResponse.failedCustom(BaseCode.getCode(000, "PARAM_CODE_OF_MAC"), "解析加密后的入参异常吗，请检查发送的电视参数").build();
		}
		//我都需要什么数据呢
		//userName=wangzhan&password=wangzhan0418&mac=FCD50900B3CD&soundServerAddress=ssssss&upgradeServerAddress=oooooo&platformType=1
		//final String mac = org.codehaus.plexus.util.StringUtils.equals(param[0].split("=")[0],"mac")?param[0].split("=")[1]:"";  //电视mac地址
		return BaseResponse.successCustom().build();


	}


}
