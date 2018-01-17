package com.iptv.model.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseEntity  {

	private String pageNo;
	
	private String beginTim;
	
	private String endTim;

}
