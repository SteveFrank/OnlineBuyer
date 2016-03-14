package com.ordering.user.dao;

import com.ordering.user.po.User;

public interface UserDao {
	public User findByUsername(String username);
	public User findByEmail(String email);
	public User findByCode(String code);
	public void updateState(String uid,boolean state);
	public void add(User user);
}
