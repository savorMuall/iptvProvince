package com.iptv.controller.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
 * 拦截器设置默认访问路径
 * 
 * */
public class DefaultInterceptor implements HandlerInterceptor{

	private String rootUrl;
	
	private String webBaseUrl;
	
	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getWebBaseUrl() {
		return webBaseUrl;
	}

	public void setWebBaseUrl(String webBaseUrl) {
		this.webBaseUrl = webBaseUrl;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		req.setAttribute("rootUrl", rootUrl);
		req.setAttribute("webBaseUrl", webBaseUrl);
		return true;
	}

}
