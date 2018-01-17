package com.iptv.model.area.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 省份表
 */
@Data
public class Area implements Serializable{
	
	private static final long serialVersionUID = -5069706792375398655L;

	//主键id
	private int id;
	
	//省份名称
	private String name;
	
	//创建时间
	private Date createTime;
	
	//更新时间
	private Date updateTime;

	

}
