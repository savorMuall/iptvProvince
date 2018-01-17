package com.iptv.model.city.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class City implements Serializable{

	private static final long serialVersionUID = -4310160326276996972L;

	//地市表主键id
	private int id;
	
	//地市id
	private int cityId;
	
	//城市名称
	private String cityName;
	
	//城市拼音
	private String cityPy;
	
	//省份id
	private int provId;
	
	//0 - 禁用 1 - 启用 
	private String cityState;

	public String toString(){
		return "{\"id\":"+id+",\"cityId\":"+cityId+",\"cityName\":\""+cityName+"\","
			  + "\"cityPy\":\""+cityPy+"\",\"provId\":"+provId+",\"cityState\":\""+cityState+"\"}";
	}
	
	
}
