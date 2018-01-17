package com.iptv.service.impl;

import com.iptv.dao.area.AreaMapper;
import com.iptv.model.area.entity.Area;
import com.iptv.service.area.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaMapper areaMapper;

	public List<Area> findAll(Area area) {
		
		return areaMapper.findAll(area);
	}

	public Area find(Area area) {
		
		return areaMapper.find(area);
	}

}
