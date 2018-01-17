package com.iptv.model.user.helper;

import java.io.Serializable;

public class AuthArea implements Serializable {

	private static final long serialVersionUID = 8052694827146696537L;

	private int id;
	
	private int userId;
	
	private int areaId;
	
	private String areaName;
	
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

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
