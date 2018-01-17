package com.iptv.model.tv.helper;

import lombok.Data;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视对于区域entity
 * @encoding UTF-8
 * @date 2017/03/28
 * @time 13:29
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Data
public class TvArea {
	
	/**
	 * 主键id
	 */
	private int id;
	
	/**
	 * 省份编码
	 */
	private int provinceCode;
	
	/**
	 * 地市编码
	 */
	private int cityCode;
	
	/**
	 * 电视mac地址
	 */
	private String mac;

}
