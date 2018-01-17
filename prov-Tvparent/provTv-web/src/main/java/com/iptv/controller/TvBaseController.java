package com.iptv.controller;

import com.iptv.model.city.entity.City;
import com.iptv.model.platform.entity.Platform;
import com.iptv.model.tv.helper.SearchTv;
import com.iptv.service.city.CityService;
import com.iptv.service.platform.PlatformService;
import com.iptv.service.tv.TvBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 省份电视基础信息对应controller
 * @author w_z91
 * @date 2017/04/06
 * @version 1.0
 */
@Slf4j
@Controller
public class TvBaseController {
	
	@Autowired
	private TvBaseService tvBaseService;

	@Autowired
	private PlatformService platformService;

	@Autowired
	private CityService cityService;
	
	private final static String MODEL = "tvShow";

	@RequestMapping(value = "findTvBase.do")
	public ModelAndView findTv(@ModelAttribute("gForm") SearchTv tv, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		try{
			//查询出平台名称 地市
			List<Platform> platforms = this.platformService.getAllPlatform();

			//查询出地市列表
			List<City> cities = this.cityService.getAllCity();






		}catch(Exception e){
			e.printStackTrace();
		}








		/*try {
			log.info("进入电视节目查询后台");
			String pageNo = tv.getPageNo(); // 查询页码
			PageEntity pa = this.getPageEntity(pageNo);
			if (StringUtils.isEmpty(pageNo)) {
				pa.setCurrentPage(1);
			} else {
				pa.setCurrentPage(Integer.parseInt(pageNo));
			}
			int pageAll = 0;
			List<Tv> tvs = new ArrayList<Tv>();
			List<Tv> tvs1 = new ArrayList<Tv>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startNumber", pa.getStartNumber());
			map.put("endNumber", pa.getPageSize());
			tvs = this.tvService.selectTvAllFy(map);

			tvs1 = this.tvService.findAll(new Tv());
			// 获取总页数
			if (null != tvs1 && tvs1.size() > 0) {
				pageAll = getPageAll(tvs1.size(), pa.getPageSize());
			}

			// 查询省份
			List<Province> provinces = new ArrayList<Province>();
			provinces = this.provinceService.findAll(new Province());
			modelAndView.addObject("provinces", provinces);

			List<Integer> listpaNo = new ArrayList<Integer>();
			listpaNo = this.getPaN0(pageAll, pa.getCurrentPage());
			modelAndView.addObject("listpaNo", listpaNo);
			modelAndView.addObject("pageAll", pageAll);
			modelAndView.addObject("page", pa);
			modelAndView.addObject("pageNum", pa.getCurrentPage());
			modelAndView.addObject("beginNum", pa.getStartNumber());
			modelAndView.addObject("statusBase",
					pa.getCurrentPage() == 1 ? 0 : (pa.getCurrentPage() - 1) * pa.getPageSize());

			modelAndView.addObject("tvs", tvs);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}*/
		modelAndView.setViewName(String.format("/%s/tvShow", MODEL));
		return modelAndView;
	}


	  
	
	
	
	
	
}
