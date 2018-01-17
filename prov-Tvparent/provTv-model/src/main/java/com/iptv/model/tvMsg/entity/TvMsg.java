package com.iptv.model.tvMsg.entity;

import java.io.Serializable;
import java.util.Date;

public class TvMsg implements Serializable{

	private static final long serialVersionUID = 7446614064943658411L;

	//电视机表主键id
	private int id;
	
	//批次类型
	private String  deviceId;
	
	//批次名称
	private String deviceName;
	
	//设备类型
	private String deviceType;
	
	//数量
	private int	number;
	
	//操作系统
	private String operateSystem;
	
	//电视机型号
	private String tvModel;
	
	//省份表id
	private int areaId;
	
	//创建时间
	private Date createTime;
	
	//更新时间
	private Date updateTime;
	
	public String toString(){
		return "{\"id\":"+id+",\"deviceId\":\""+deviceId+"\",\"deviceName\":\""+deviceName+"\",\"deviceType\":\""+deviceType+"\","
			  + "\"number\":"+number+",\"operateSystem\":\""+operateSystem+"\",\"tvModel\":\""+tvModel+"\",\"areaId\":"+areaId+","
			  + "\"createTime\":\""+createTime+"\",\"updateTime\":\""+updateTime+"\"}";
	}
	

	/*
	 * get 电视机表主键id
	 */
	public int getId() {
		return id;
	}

	/*
	 * set 电视机表主键id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * get 批次类型
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/*
	 * set 批次类型
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/*
	 * get 批次名称
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/*
	 * set 批次名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/*
	 * get 设备类型
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/*
	 * set 设备类型
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/*
	 * get 数量
	 */
	public int getNumber() {
		return number;
	}

	/*
	 * set 数量
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/*
	 * get 操作系统
	 */
	public String getOperateSystem() {
		return operateSystem;
	}

	/*
	 * set 操作系统
	 */
	public void setOperateSystem(String operateSystem) {
		this.operateSystem = operateSystem;
	}

	/*
	 * get 电视机型号
	 */
	public String getTvModel() {
		return tvModel;
	}

	/*
	 * set 电视机型号
	 */
	public void setTvModel(String tvModel) {
		this.tvModel = tvModel;
	}

	/*
	 * get 省份表id
	 */
	public int getAreaId() {
		return areaId;
	}

	/*
	 * set 省份表id
	 */
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	/*
	 * get 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/*
	 * set 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/*
	 * get 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/*
	 * set 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
