package com.iptv.controller;

import com.iptv.model.category.entity.Category;
import com.iptv.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "category.do")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		Category searchCategory = new Category();
		searchCategory.setId(1);
		searchCategory.setName("123");
		Category category = this.categoryService.find(searchCategory);
		ModelAndView mv = new ModelAndView();
		mv.addObject("entity", category);
		mv.setViewName("category/cate");
		resp.setCharacterEncoding("UTF-8");
		return mv;
		
	}
	
	

}
