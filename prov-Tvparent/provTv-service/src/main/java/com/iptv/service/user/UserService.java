package com.iptv.service.user;


import com.iptv.model.user.entity.User;

public interface UserService {
	
	public User findUserForLogin(String userName);
}
