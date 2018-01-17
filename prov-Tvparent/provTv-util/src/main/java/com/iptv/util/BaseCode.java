package com.iptv.util;

import org.springframework.stereotype.Component;

/**
 * 系统基础编码
 * @author w_z91
 *
 */
@Component
public class BaseCode {
	
	public static final int SUCCESS_CODE= 200; //正确返回代码
	
	public static final int AREA_HAVE_THIS_TV = 1001 ; //省份有该电视
	
	public static final int AREA_HAVE_THIS_TV_HAVE_ACTIVATION = 1002 ; //省份有该电视，同时电视已经激活
	
	public static final int AREA_HAVE_THIS_TV_NO_ACTIVATION = 1003 ; //省份有该电视，同时电视未激活
	
	public static final int AREA_HAVE_THIS_TV_NO_TVMESSAGE = 1004 ; //省份有该电视，但是查询不到电视信息
	
	public static final int AREA_NOT_HAVE_THIS_TV = 1010 ; //省份没有该电视
	
	public static final int PARAM_CODE_OF_MAC = 2001 ; //查詢所需的mac地址空
	
	public static final int FIND_TV_BY_CODE_ERROR = 3001 ; //根據mac地址查詢電視信息失敗

	private static final int IPTV_BINDING_PARAMETER_ERROR = 5001 ; //接收的 mac与ip的绑定参数解析失败

	public static int getCode(int provinceCode,String type){	
		switch(type){
			case "AREA_NOT_HAVE_THIS_TV": return Integer.parseInt(provinceCode+""+ BaseCode.AREA_NOT_HAVE_THIS_TV);
			case "AREA_HAVE_THIS_TV": return Integer.parseInt(provinceCode+""+ BaseCode.AREA_HAVE_THIS_TV);
			case "AREA_HAVE_THIS_TV_NO_TVMESSAGE": return Integer.parseInt(provinceCode+""+ BaseCode.AREA_HAVE_THIS_TV_NO_TVMESSAGE);
			default : return Integer.parseInt(provinceCode+"0000");
		}
	}

}
