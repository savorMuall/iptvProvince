package com.iptv.service.impl;

import com.iptv.dao.brand.BrandMapper;
import com.iptv.model.brand.entity.Brand;
import com.iptv.service.brand.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandMapper brandMapper;
	
	public List<Brand> findAll(Brand brand) {
		
		return brandMapper.findAll(brand);
	}

	public int insert(Brand brand) {

		return brandMapper.insert(brand);
	}

	public List<Brand> selectBrandAllFy(Map<String, Object> map) {
		
		return brandMapper.selectBrandAllFy(map);
	}

}
