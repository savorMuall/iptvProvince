package com.iptv.service.impl;

import com.iptv.dao.city.CityMapper;
import com.iptv.model.city.entity.City;
import com.iptv.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityMapper cityMapper;

	public List<City> findAll(int provId) {
		
		return cityMapper.findAll(provId);
	}

	@Override
	public List<City> getAllCity() {
		return cityMapper.getAllCity();
	}

}
