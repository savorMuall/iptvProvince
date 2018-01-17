package com.iptv.controller;

import com.iptv.model.brand.entity.Brand;
import com.iptv.model.brand.helper.SearchBrand;
import com.iptv.service.brand.BrandService;
import com.iptv.util.PageEntity;
import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 厂家维护
 * @author iptv-wangzhan
 * @version 1.0
 * date:2017/01/18
 */

@Controller
public class BrandController {

	private final Logger log = LoggerFactory.getLogger(BrandController.class);
	
	@Autowired
	private BrandService brandService;
	
	private final static String MODEL = "brandShow";
	
	@RequestMapping(value = "findBrand.do")
	public ModelAndView findBrand(@ModelAttribute("gForm") SearchBrand searchBrand, HttpServletRequest req, HttpServletResponse resp){
		
		ModelAndView modelAndView = new ModelAndView();
		try{
			log.info("进入厂家查询后台");
			String  pageNo = searchBrand.getPageNo(); //查询页码
			PageEntity pa = this.getPageEntity(pageNo);
			if(StringUtils.isEmpty(pageNo)){
				pa.setCurrentPage(1);
			}else{
				pa.setCurrentPage(Integer.parseInt(pageNo));
			}
	 		int pageAll = 0;
	 		List<Brand> brands = new ArrayList<Brand>();
	 		List<Brand> brands1 = new ArrayList<Brand>();
	 		Map<String,Object> map = new HashMap<String,Object>();
	 		map.put("startNumber",pa.getStartNumber());
	 		map.put("endNumber", pa.getPageSize());
	 		brands = this.brandService.selectBrandAllFy(map);
	 		brands1 = this.brandService.findAll(new Brand());
	 		//获取总页数
			if(null!=brands1 && brands1.size()>0){
				pageAll = getPageAll(brands1.size(),pa.getPageSize());
			}
			List<Integer> listpaNo = new ArrayList<Integer>();
	 		listpaNo = this.getPaN0(pageAll,pa.getCurrentPage());
	 		modelAndView.addObject("listpaNo", listpaNo);
	 		modelAndView.addObject("pageAll", pageAll);
	 		modelAndView.addObject("page",pa);
	 		modelAndView.addObject("pageNum",pa.getCurrentPage());
	 		modelAndView.addObject("beginNum",pa.getStartNumber());
	 		modelAndView.addObject("statusBase",pa.getCurrentPage()==1?0:(pa.getCurrentPage()-1)*pa.getPageSize());
	 		modelAndView.addObject("brands", brands);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		modelAndView.setViewName(String.format("/%s/brandShow", MODEL));
		return modelAndView;
		
		
	} 
	
	public PageEntity getPageEntity(String pageNo){
		PageEntity pa = new PageEntity();
		if(StringUtils.isEmpty(pageNo)){
 			//首次进入，默认查询第一页数据
 			pa.setPageNo(1);
 			pa.setStartNumber(0);
 			pa.setEndNumber(pa.getPageSize());
 		}else{
 			pa.setPageNo(Integer.parseInt(pageNo));
 			pa.setStartNumber(StringUtils.equals("1",pageNo)?0:pa.getPageSize()*(Integer.parseInt(pageNo)-1));
 			pa.setEndNumber(pa.getPageSize());
 		}
		return pa;
	}
	
	/*
	 * 返回页码list   模仿百度分页展示形式
	 * @param pageAll  总页数
	 * @param currentPage 当前页
	 * @return
	 */
	public List<Integer> getPaN0(int pageAll,int currentPage){
		List<Integer> list = new ArrayList<Integer>();
		int basicIn = 1;
		if(pageAll>10){
			if(currentPage>6){
				if(pageAll-currentPage >4){
					list.add(currentPage-5);
					list.add(currentPage-4);
					list.add(currentPage-3);
					list.add(currentPage-2);
					list.add(currentPage-1);
					list.add(currentPage);
					list.add(currentPage+1);
					list.add(currentPage+2);
					list.add(currentPage+3);
					list.add(currentPage+4);
				}else{
					int i = pageAll-currentPage;
					int j = 10-i;
					for(int v = j; v>0; v--){
						list.add(currentPage-v);
					}
					for(int k = 0;k<=i;k++){
						list.add(currentPage+k);
					}
				}
			}else{
				for(;basicIn<=10;basicIn++){
					list.add(basicIn);
				}
			}
		}else{
			for(int ld = 1;ld<=pageAll;ld++){
				list.add(ld);
			}
		}
		return list;
	}
	
	public int getPageAll(int listSize,int pageSize){
		return listSize/pageSize + (listSize%pageSize>0?1:0);
	}
}
