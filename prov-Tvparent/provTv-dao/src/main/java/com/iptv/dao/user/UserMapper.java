package com.iptv.dao.user;


import com.iptv.model.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	
	public User findUserForLogin(String userName);
}
