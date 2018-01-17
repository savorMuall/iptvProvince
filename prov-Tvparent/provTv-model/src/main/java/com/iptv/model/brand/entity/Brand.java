package com.iptv.model.brand.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 厂家基本信息表对应实体entity
 * @author iptv-wangzhan
 * @version 1.0
 * date: 2017/01/18
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Brand implements Serializable{

	private static final long serialVersionUID = -7888779444895636760L;

	//厂家基本信息表主键id
	private int id;
	
	//厂家名称
	private String name;

	//型号
	private String model;
	
	//芯片
	private String chip;
	
	//安卓版本
	private String androidVersion;
	
	//rom内存大小
	private String romMemory;
	
	//主频
	private String freQuency;
	
	//编号  16位字符串
	private String number;
	
	//创建时间
	private Date createTime;
	
	//更新时间
	private Date updateTime;

	
}
