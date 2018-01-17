package com.iptv.model.brand.helper;

import com.iptv.model.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchBrand extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -893809635413398256L;

	// 厂家名称
	private String name;

	// 型号
	private String model;

	// 芯片
	private String chip;

	// 安卓版本
	private String androidVersion;

	// rom内存大小
	private String romMemory;

	// 主频
	private String freQuency;

	// 编号 16位字符串
	private String number;

	// 创建时间
	private Date createTime;

	// 更新时间
	private Date updateTime;

}
