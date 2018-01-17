package com.iptv.model.user.helper;

import java.io.Serializable;

/**
 * 用户登录记录表
 * @author savor-java
 *
 */

public class UserLoginRecordEntity implements Serializable{

	private static final long serialVersionUID = 7668351766089139281L;

	//主键id
	private int id;
	
	//主机ip
	private String ip;
	
	//错误次数
	private int error_time;
	
	//时间
	private String data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getError_time() {
		return error_time;
	}

	public void setError_time(int error_time) {
		this.error_time = error_time;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
	
}
