package com.iptv.service.impl;

import com.iptv.dao.province.ProvinceMapper;
import com.iptv.model.province.entity.Province;
import com.iptv.service.province.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceMapper provinceMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return provinceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Province record) {
		return provinceMapper.insert(record);
	}

	@Override
	public int insertSelective(Province record) {
		return provinceMapper.insertSelective(record);
	}

	@Override
	public Province selectByPrimaryKey(Integer id) {
		return provinceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Province record) {
		return provinceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Province record) {
		return provinceMapper.updateByPrimaryKey(record);
	}

	public List<Province> findAll(Province p) {
		
		return provinceMapper.findAll(p);
	}

	@Override
	public Province selectByProvId(int provId) {
		return provinceMapper.selectByProvId(provId);
	}

}
