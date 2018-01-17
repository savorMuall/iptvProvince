package com.iptv.model.user.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -4545906235881985622L;

	//用户id
	private int id;
	
	//用户名
	private String userName;
	
	//密码md5值
	private String password;
	
	//用户创建时间
	private String createTime;
	
	//用户信息更新时间
	private String updateTime;
	
	private int dept;
	
	private int role;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
