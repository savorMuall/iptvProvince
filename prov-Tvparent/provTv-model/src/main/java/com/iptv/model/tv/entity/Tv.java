package com.iptv.model.tv.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 电视基本信息表对应实体entity
 * @author iptv-wangzhan
 * @version 1.0
 * date : 2017/01/18
 */
public class Tv implements Serializable{

	private static final long serialVersionUID = 4931920506740360775L;

	//电视的表主键id
	private int id;
	
	//厂家id
	private int brandId;
	
	//电视mac地址
	private String mac;
	
	//电视所在省份
	private String provId;
	
	//电视所在地市
	private String cityId;
	
	//平台（烽火、中兴、华为）
	private String platform;
	
	//批次类型
	private String  deviceId;
	
	//批次名称
	private String deviceName;
	
	//设备类型
	private String deviceType;
	
	//数量
	private int	number;
	
	//升级服务器地址
	private String upgradeServerAddress;
	
	//健全服务器地址
	private String soundServerAddress;
	
	//apk版本号
	private String apk;
	
	//rom版本号
	private String rom;
	
	//播放器版本号
	private String playerVersion;
	
	//浏览器版本号
	private String browserVersion;
	
	//激活状态（1->激活、2->未激活、3->冻结）
	private byte state;
	
	//账号
	private String user;
	
	//密码
	private String pass;
	
	//创建时间
	private Date createTime;
	
	//更新时间
	private Date updateTime;
	
	//厂家名称
	private String brandName;
	
	private String provName;

	/*
	 * get 电视的表主键id
	 */
	public int getId() {
		return id;
	}

	/*
	 * set 电视的表主键id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/*
	 * get 厂家id
	 */
	public int getBrandId() {
		return brandId;
	}

	/*
	 * set 厂家id
	 */
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	/*
	 * get 电视mac地址
	 */
	public String getMac() {
		return mac;
	}

	/*
	 * set 电视mac地址
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/*
	 * get 电视所在地市
	 */
	public String getCityId() {
		return cityId;
	}

	/*
	 * set 电视所在地市
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/*
	 * get 平台（烽火、中兴、华为）
	 */
	public String getPlatform() {
		return platform;
	}

	/*
	 * set 平台（烽火、中兴、华为）
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/*
	 * get 升级服务器地址
	 */
	public String getUpgradeServerAddress() {
		return upgradeServerAddress;
	}

	/*
	 * set 升级服务器地址
	 */
	public void setUpgrade_server_address(String upgradeServerAddress) {
		this.upgradeServerAddress = upgradeServerAddress;
	}

	/*
	 * get 健全服务器地址
	 */
	public String getSoundServerAddress() {
		return soundServerAddress;
	}

	/*
	 * set 健全服务器地址
	 */
	public void setSound_server_address(String soundServerAddress) {
		this.soundServerAddress = soundServerAddress;
	}

	/*
	 * get apk版本号
	 */
	public String getApk() {
		return apk;
	}

	/*
	 * set apk版本号
	 */
	public void setApk(String apk) {
		this.apk = apk;
	}

	/*
	 * get rom版本号
	 */
	public String getRom() {
		return rom;
	}

	/*
	 * set rom版本号
	 */
	public void setRom(String rom) {
		this.rom = rom;
	}

	/*
	 * get 播放器版本号
	 */
	public String getPlayerVersion() {
		return playerVersion;
	}

	/*
	 * set 播放器版本号
	 */
	public void setPlayerVersion(String playerVersion) {
		this.playerVersion = playerVersion;
	}

	/*
	 * get 浏览器版本号
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/*
	 * set 浏览器版本号
	 */
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	/*
	 * get 激活状态（1->激活、2->未激活、3->冻结）
	 */
	public byte getState() {
		return state;
	}

	/*
	 * set 激活状态（1->激活、2->未激活、3->冻结）
	 */
	public void setState(byte state) {
		this.state = state;
	}

	/*
	 * get 账号
	 */
	public String getUser() {
		return user;
	}

	/*
	 * set 账号
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/*
	 * get 密码
	 */
	public String getPass() {
		return pass;
	}

	/*
	 * set 密码
	 */
	public void setPass(String pass) {
		this.pass = pass;
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
	public void setCreate_time(Date createTime) {
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setUpgradeServerAddress(String upgradeServerAddress) {
		this.upgradeServerAddress = upgradeServerAddress;
	}

	public void setSoundServerAddress(String soundServerAddress) {
		this.soundServerAddress = soundServerAddress;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}
	
	
	
	
}
