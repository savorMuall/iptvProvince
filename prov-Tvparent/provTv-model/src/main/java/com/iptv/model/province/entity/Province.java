package com.iptv.model.province.entity;

/**
 * 省份表对应实体entity
 * @author iptv-wangzhan
 * @version 1.0
 * data : 2017/01/19
 */
public class Province {
	
	//省份表主键id
	private int id;
	
	//省份id
	private int provId;
	
	//省份名称
	private String provName;
	
	/*1 - 直辖市
	 *2 - 行政省
	 *3 - 自治区
	 *4 - 特别行政区
	 *5 - 其他国家
	 * 见全局数据字典[省份类型] 
	 */
	private String provType;
	
	//0 - 禁用  1 - 启用 
	private String provState;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProvId() {
		return provId;
	}

	public void setProvId(int provId) {
		this.provId = provId;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getProvType() {
		return provType;
	}

	public void setProvType(String provType) {
		this.provType = provType;
	}

	public String getProvState() {
		return provState;
	}

	public void setProvState(String provState) {
		this.provState = provState;
	}
	
	
	
}
