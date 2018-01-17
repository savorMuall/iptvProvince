package com.iptv.controller;

import com.iptv.util.BaseUtil;
import com.iptv.util.GlobalShareMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CharacterEncodingFilter extends
		org.springframework.web.filter.CharacterEncodingFilter {
	
	private static final Logger log = LoggerFactory
	.getLogger(CharacterEncodingFilter.class);
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String uri = request.getRequestURI();
		if (uri.contains("tv_msg.do")){
			super.doFilter(request, response, filterChain);
			return;
		}
		if (uri.contains("getTvMsg.do")){
			super.doFilter(request, response, filterChain);
			return;
		}
		if (uri.contains(".do")){
			if (BaseUtil.authenLoginExpire(request)){
				filterChain.doFilter(request, response);
			} else {
 				String username = (String) request.getSession().getAttribute("userName");
				if (username != null && !username.isEmpty()){
					long now = System.currentTimeMillis();
					WebUtils.setSessionAttribute(request,"isLogin", now);
					if (!auth(request, uri)) {
						request.getRequestDispatcher("/login.do").forward(request, response);
					} else {
						filterChain.doFilter(request, response);
					}
				} else {
					//response.sendRedirect("/smsp/jsp/index.jsp"); 
					request.getRequestDispatcher("/login.do").forward(request, response);
					//filterChain.doFilter(request, response);
					//super.doFilter(request, response, filterChain);
				}
			}
		} else {
			super.doFilter(request, response, filterChain);
		}
	}

	@Override
	public void setEncoding(String encoding) {
		super.setEncoding(encoding);
	}

	@Override
	public void setForceEncoding(boolean forceEncoding) {
		super.setForceEncoding(forceEncoding);
	}
	
	private boolean auth(HttpServletRequest request, String uri) {
		String surfix = uri.split("/")[1];
		int userId = (Integer) WebUtils.getSessionAttribute(request, "id");
		
		logger.info("CharacterEncoding.auth: userId="+ userId);
		GlobalShareMemory mem = GlobalShareMemory.getInstance();
		return mem.contains(userId, surfix);
	}
}
