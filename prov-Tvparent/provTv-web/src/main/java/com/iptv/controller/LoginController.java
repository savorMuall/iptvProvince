package com.iptv.controller;

import com.iptv.model.user.entity.User;
import com.iptv.model.user.helper.AuthArea;
import com.iptv.model.user.helper.AuthList;
import com.iptv.model.user.helper.PageUri;
import com.iptv.model.user.helper.UserLoginRecordEntity;
import com.iptv.service.user.UserService;
import com.iptv.util.GlobalShareMemory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "login.do")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		
		String msg = "";
		ModelAndView mv = new ModelAndView();
		if ((userName != null && !userName.isEmpty())&& (password != null && !password.isEmpty())){
			User user = this.userService.findUserForLogin(userName);
				if (user != null){
					WebUtils.setSessionAttribute(req, "userName", userName);
					WebUtils.setSessionAttribute(req, "id", user.getId());
					WebUtils.setSessionAttribute(req, "isLogin", System.currentTimeMillis());
					
					expire();
					return new ModelAndView("redirect:/index.do");
				} else {
					msg = "用户名或密码错误";
					//获取当前用户ip
					 InetAddress address = InetAddress.getLocalHost();   
				     String ip = address.getHostAddress();
				     List<UserLoginRecordEntity>  list = new ArrayList<UserLoginRecordEntity>();
				     if(StringUtils.isNotBlank(ip)){
				    	 //根据ip查找当前用户今天还可尝试登陆次数
				    	 UserLoginRecordEntity userLoginRecordEntity = new UserLoginRecordEntity();
				    	 userLoginRecordEntity.setIp(ip);
				    	 userLoginRecordEntity.setData(new SimpleDateFormat("yyyyMMdd").format(new Date()));
				    	// list = userLoginRecordService.findEntityListByCondition(userLoginRecordEntity);
				    	 if(null!=list && list.size()>0){
				    		 UserLoginRecordEntity userLoginRecordEntityOne = list.get(0);
				    		 int time =  userLoginRecordEntityOne.getError_time();
				    		 if(time>=5){
				    			 msg="您好，您今天用户名密码连续输错5次，今天无法登录系统";
				    		 }else{
				    			 //修改今天的登录数据中错误次数
				    			 userLoginRecordEntityOne.setError_time(userLoginRecordEntityOne.getError_time()+1);
				    		//	 userLoginRecordService.saveOrUpdateObject(userLoginRecordEntityOne);
				    		 }
				    	 }else{
				    		 //插入今天的登陆数据
				    		 List<UserLoginRecordEntity>  listToday = new ArrayList<UserLoginRecordEntity>();
				    		 UserLoginRecordEntity userLoginRecordEn = new UserLoginRecordEntity();
				    		 userLoginRecordEn.setData(new SimpleDateFormat("yyyyMMdd").format(new Date()));
				    		 userLoginRecordEn.setError_time(1);
				    		 userLoginRecordEn.setIp(ip);
				    		 listToday.add(userLoginRecordEn);
				    //		 userLoginRecordService.insertUserLoginRecord(listToday);
				    	 }
				    	 
				     }
				}
		} 
		
		mv.setViewName("login");
		mv.addObject("msg", msg);
		return mv;
	}
	
	/**
	 * 每添加一个用户，就初始化一次
	 */
	private synchronized void expire() {
		GlobalShareMemory mem = GlobalShareMemory.getInstance();
		if (mem.isNotEmpty()) {
			long now = System.currentTimeMillis();
			
			if (mem.containsKey(-1)) {
				long start = mem.getValue(-1);
				if ((now - start) >= 1 * 60 * 60 * 1000) {
					mem.clear();
					
					initMem(mem);
				}
			}
		} else {
			initMem(mem);
			
		
			boolean a = mem.contains(9, "index.do");
			System.out.println("=="+a);
		}
	}
	
	public void initMem(GlobalShareMemory mem) {
		List<AuthList> authList = new ArrayList<AuthList>();//this.userService.findAllUris();
		Set<String> uris = new HashSet<String>();
		if (authList != null && !authList.isEmpty()) {
			for (AuthList auth : authList) {
				mem.add(auth.getUserId(), auth.getUri());
			}
			
			List<PageUri> pageUris =new ArrayList<PageUri>();// this.userService.findAllPageUris();
			if (pageUris != null && !pageUris.isEmpty()){
				for (PageUri pageUri : pageUris) {
					uris.add(pageUri.getUri());
				}
			}
			mem.put(-1, System.currentTimeMillis());
			mem.put(-2, uris);
		} 
	}
	
	@RequestMapping(value = "index.do")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		
		int userId = (Integer) WebUtils.getSessionAttribute(req, "id");
		List<AuthArea> areas = new ArrayList<AuthArea>();
		ModelAndView mv = new ModelAndView();
	
		mv.setViewName("index");
		resp.setCharacterEncoding("UTF-8");
		return mv;
		
	}
	
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest req,
                               HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		session.removeAttribute("isLogin");
		session.removeAttribute("userName");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
}
