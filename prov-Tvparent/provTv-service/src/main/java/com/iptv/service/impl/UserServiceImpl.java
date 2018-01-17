package com.iptv.service.impl;

import com.iptv.dao.user.UserMapper;
import com.iptv.model.user.entity.User;
import com.iptv.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	public User findUserForLogin(String userName) {
		
		return userMapper.findUserForLogin(userName);
	}

}
