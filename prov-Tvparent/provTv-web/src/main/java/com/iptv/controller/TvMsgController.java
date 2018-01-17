package com.iptv.controller;

import com.iptv.model.tv.helper.SearchTv;
import com.iptv.model.tvMsg.entity.TvMsg;
import com.iptv.service.tvMsg.TvMsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class TvMsgController {
	private final static String MODEL = "tvShow";
	 
	@Autowired
	private TvMsgService tvMsgService;

	@RequestMapping(value = "findTv.do")
	public ModelAndView findTv(@ModelAttribute("SearchTv") SearchTv tv, HttpServletRequest req, HttpServletResponse resp)throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		try{
			log.info("进入电视节目查询后台,查询条件是:"+tv.toString());
			List<TvMsg> tvMsgs = new ArrayList<TvMsg>();
			tvMsgs = this.tvMsgService.findAll(new TvMsg());
			modelAndView.addObject("tvMsgs", tvMsgs);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
		modelAndView.setViewName(String.format("/%s/tvShow", MODEL));
		return modelAndView;
	}
	
	@RequestMapping(value = "queryTv.do")
	public void queryTv(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		
		TvMsg tvMsg = new TvMsg();
		log.info("进入电视节目查询后台,查询条件是:"+tvMsg.toString());
		List<TvMsg> tvMsgs = new ArrayList<TvMsg>();
		tvMsgs = this.tvMsgService.findAll(tvMsg);
		
		int statusN = 0;
        statusN = HttpServletResponse.SC_OK;
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter pw = null;
        try {
            resp.setStatus(statusN);
            pw = resp.getWriter();
            pw.print("{\"success\":true,\"flag\":\""+"1"+"\",\"tvMsgs\":"+tvMsgs.toString()+"}");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null){
                pw.close();
            }
        }
	}
}
