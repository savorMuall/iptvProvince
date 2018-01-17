package com.iptv.dao.category;


import com.iptv.model.category.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {
	
	public Category find(Category category);

}
