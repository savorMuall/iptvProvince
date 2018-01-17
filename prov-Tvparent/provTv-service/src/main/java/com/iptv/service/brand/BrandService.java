package com.iptv.service.brand;


import com.iptv.model.brand.entity.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {
	
	/*
	 * 根据查询条件查找，可查找指定，也可全表都查出来
	 */
	public List<Brand>  findAll(Brand brand);
	
    /*
     * 分页查询所有的厂家对象
     * @return List<Brand>
     * date:2017/01/20
     */
    public List<Brand> selectBrandAllFy(Map<String, Object> map);
	
	/*
	 * 插入厂家信息
	 */
	int insert(Brand brand);

	
	
}
