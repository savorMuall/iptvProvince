package com.iptv.controller;

import com.iptv.model.city.entity.City;
import com.iptv.model.province.entity.Province;
import com.iptv.model.tv.entity.Tv;
import com.iptv.model.tv.helper.SearchTv;
import com.iptv.service.city.CityService;
import com.iptv.service.province.ProvinceService;
import com.iptv.service.tv.TvService;
import com.iptv.util.DateUtils;
import com.iptv.util.ExcelImportUtil;
import com.iptv.util.PageEntity;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电视
 * 
 * @author iptv-wangzhan
 * @version 1.0 date:2017/01/18
 */
@Slf4j
@Controller
public class TvController {

	@Autowired
	private TvService tvService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

	private final static String MODEL = "tvShow";

	@RequestMapping(value = "findProvTv.do")
	public ModelAndView findTv(@ModelAttribute("gForm") SearchTv tv, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		try {
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
		}
		modelAndView.setViewName(String.format("/%s/tvShow", MODEL));
		return modelAndView;
	}

	@RequestMapping(value = "importExel.do")
	public String importExel(@RequestParam(value = "excelFile") MultipartFile excelFile, HttpServletRequest request,
                             HttpServletResponse resp) throws IOException {
		try {
			String fileName = excelFile.getOriginalFilename();// report.xls
			String fileName2 = excelFile.getName();// excelFile
			InputStream fis = null;
			fis = excelFile.getInputStream();
			List<List<Object>> listob = null;
			listob = ExcelImportUtil.parseExcel(fis, fileName);
			fis.close();
			List<Tv> tvs = new ArrayList<Tv>();
			for (int i = 0; i < listob.size(); i++) {
				List<Object> lo = listob.get(i);
				Tv tv = new Tv();
				tv.setMac(String.valueOf(lo.get(0)));
				tv.setDeviceName(String.valueOf(lo.get(1)));
				tv.setCreate_time(DateUtils.getNowDateyMDdHms());
				tvs.add(tv);
			}
			this.tvService.insertList(tvs);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "forward:/findProvTv.do";

	}

	public PageEntity getPageEntity(String pageNo) {
		PageEntity pa = new PageEntity();
		if (StringUtils.isEmpty(pageNo)) {
			// 首次进入，默认查询第一页数据
			pa.setPageNo(1);
			pa.setStartNumber(0);
			pa.setEndNumber(pa.getPageSize());
		} else {
			pa.setPageNo(Integer.parseInt(pageNo));
			pa.setStartNumber(StringUtils.equals("1", pageNo) ? 0 : pa.getPageSize() * (Integer.parseInt(pageNo) - 1));
			pa.setEndNumber(pa.getPageSize());
		}
		return pa;
	}

	/*
	 * 返回页码list 模仿百度分页展示形式
	 * 
	 * @param pageAll 总页数
	 * 
	 * @param currentPage 当前页
	 * 
	 * @return
	 */
	public List<Integer> getPaN0(int pageAll, int currentPage) {
		List<Integer> list = new ArrayList<Integer>();
		int basicIn = 1;
		if (pageAll > 10) {
			if (currentPage > 6) {
				if (pageAll - currentPage > 4) {
					list.add(currentPage - 5);
					list.add(currentPage - 4);
					list.add(currentPage - 3);
					list.add(currentPage - 2);
					list.add(currentPage - 1);
					list.add(currentPage);
					list.add(currentPage + 1);
					list.add(currentPage + 2);
					list.add(currentPage + 3);
					list.add(currentPage + 4);
				} else {
					int i = pageAll - currentPage;
					int j = 10 - i;
					for (int v = j; v > 0; v--) {
						list.add(currentPage - v);
					}
					for (int k = 0; k <= i; k++) {
						list.add(currentPage + k);
					}
				}
			} else {
				for (; basicIn <= 10; basicIn++) {
					list.add(basicIn);
				}
			}
		} else {
			for (int ld = 1; ld <= pageAll; ld++) {
				list.add(ld);
			}
		}
		return list;
	}

	public int getPageAll(int listSize, int pageSize) {
		return listSize / pageSize + (listSize % pageSize > 0 ? 1 : 0);
	}

	@RequestMapping(value = "/findCityByProvince.do")
	public void findCityByProvince(HttpServletRequest req, HttpServletResponse resp)
			throws UnsupportedEncodingException {

		req.setCharacterEncoding("UTF-8");
		String proId = req.getParameter("prov");
		boolean b = true;

		int status = 0;
		// 插入数据库成功，赋值
		status = HttpServletResponse.SC_OK;
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;

		List<City> citys = new ArrayList<City>();
		citys = this.cityService.findAll(
				StringUtils.equals(proId, null) || StringUtils.equals("0", proId) ? 0 : Integer.parseInt(proId));

		try {
			resp.setStatus(status);
			pw = resp.getWriter();
			pw.print("{\"success\":\"" + b + "\",\"name\":\"123\",\"listcitys\":" + citys + "}");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}
	
	@RequestMapping(value = "/tvProvince.do")
	public void tvProvince(HttpServletRequest req, HttpServletResponse resp)
			throws UnsupportedEncodingException {

		req.setCharacterEncoding("UTF-8");
		String tvId = req.getParameter("tvId");
		String tvMac = req.getParameter("tvMac");
		String tvProv = req.getParameter("tvProv");
		Tv tv = new Tv();
		tv.setId(Integer.parseInt(tvId));
		Tv tv1 = this.tvService.findAll(tv).get(0);
		tv1.setProvId(tvProv);
		this.tvService.update(tv1);
		boolean b = true;
		Province p = new Province();
		p.setProvId(Integer.parseInt(tvProv));
		String provName="";
		if(StringUtils.isNotBlank(tvProv)){
			provName = this.provinceService.findAll(p).get(0).getProvName();
		}
		

		int status = 0;
		// 插入数据库成功，赋值
		status = HttpServletResponse.SC_OK;
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;

		try {
			resp.setStatus(status);
			pw = resp.getWriter();
			pw.print("{\"success\":\"" + b + "\",\"provName\":\""+provName+"\"}");
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

}
