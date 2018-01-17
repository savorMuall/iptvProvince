package com.iptv.service.impl;

import com.iptv.dao.category.CategoryMapper;
import com.iptv.model.category.entity.Category;
import com.iptv.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper categoryMapper;

	public Category find(Category category) {
		
		return categoryMapper.find(category);
	}

}
