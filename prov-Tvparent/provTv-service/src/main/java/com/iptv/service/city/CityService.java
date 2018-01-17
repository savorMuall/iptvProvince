package com.iptv.service.city;


import com.iptv.model.city.entity.City;

import java.util.List;

public interface CityService {
	
	//根据省份查询地市
	public List<City> findAll(int provId);

	/*
	 * 获取本省份所有地市列表
	 */
	public List<City> getAllCity();
}
