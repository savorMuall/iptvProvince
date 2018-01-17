package com.iptv.dao.city;


import com.iptv.model.city.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {
	
	//根据省份查询地市
	public List<City> findAll(int provId);

	/*
	 * 获取本省份所有地市列表
	 */
	public List<City> getAllCity();

}
