package com.iptv.controller.util;

import net.sf.json.JSONObject;

/**
 * 返回编码说明:
 * 
 * Created by wangzhan on 2016/12/28 .
 *
 */
public class ServiceCode {

	public static String succ() {
		return succ("");
	}

	public static String succ(String msg) {
		return "{\"status\":1001, \"result\":\"" + msg + "\" }";
	}

    public static JSONObject SuccData() {
        JSONObject data = new JSONObject();
        data.put("status",1001);
        data.put("result","请求成功");
        return data;
    }

    public static JSONObject SuccData(Object object) {
        JSONObject data = new JSONObject();
        data.put("status",1001);
        data.put("result","请求成功");
        data.put("content",object);
        return data;
    }

    /**
     * 
     * @param code    1005 请求数据空
     * @param msg
     * @return
     */
	public static String err(int code, String msg) {
		return "{\"status\":" + code + ", \"result\":\"" + msg + "\" }";
	}

    /**
     * 请求超时
     *
     * @return
     */
    public static String requestTimeOut() {
        return err(1002, "请求超时");
    }

    /**
     * 错误的请求参数
     *
     * @return
     */
    public static String parameterErr() {
        return err(1003, "错误的请求参数");
    }

    /**
     * 没有更多数据
     *
     * @return
     */
    public static String notMoreData() {
        return err(1004, "没有更多数据");
    }

    /**
     * 越过客户端校验发起的请求.
     *
     * @return
     */
    public static String illegal() {
        return err(4000, "请求有误");
    }

	public static String errToken() {
		return err(4131, "您的登录已失效.");
	}

	/**
	 * 操作成功
	 */
	public final static int SUCC = 1001;

	/**
	 * token过期
	 */
	public final static int ERR_TOKEN = 4000;

}
