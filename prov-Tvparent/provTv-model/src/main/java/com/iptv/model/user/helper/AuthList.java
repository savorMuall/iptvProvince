package com.iptv.model.user.helper;

import java.io.Serializable;

public class AuthList implements Serializable {

	private static final long serialVersionUID = 8529835372636554327L;

	private int id;
	
	private int userId;
	
	private String uri;
	
	private String create_time;
	
	private String update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String updateTime) {
		update_time = updateTime;
	}
}
