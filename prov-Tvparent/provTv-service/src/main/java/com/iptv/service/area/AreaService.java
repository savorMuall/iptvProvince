package com.iptv.service.area;

import com.iptv.model.area.entity.Area;

import java.util.List;

public interface AreaService {

	/**
	 * 根据查询条件获取省份列表
	 * @param area
	 * @return
	 */
	public List<Area> findAll(Area area);
	
	/**
	 * 根据指定查询条件获取指定省份
	 * @param area
	 * @return
	 */
	public Area find(Area area);
}
