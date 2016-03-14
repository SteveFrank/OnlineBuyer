package com.ordering.user.service;

import com.ordering.user.po.User;

public interface UserService {
	public void regist(User form) throws Exception;
	public void active(String code) throws Exception;
	public User login(User form) throws Exception;
}
