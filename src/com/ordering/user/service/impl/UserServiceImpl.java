package com.ordering.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordering.user.dao.UserDao;
import com.ordering.user.po.User;
import com.ordering.user.service.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	private UserDao userDao;
	/**
	 * 用户注册
	 * @param form
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void regist(User form) throws Exception{
		/**
		 * 校验用户名
		 */
		User user = userDao.findByUsername(form.getUsername());
		if (user != null) throw new UserException("用户名已经被注册！");
		/**
		 * 校验Email
		 */
		user = userDao.findByEmail(form.getEmail());
		if (user != null) throw new UserException("邮箱名已经被注册!");
		/**
		 * 插入用户到数据库中
		 */
		userDao.add(form);
		
	}
	/**
	 * 激活功能
	 * @param code
	 * @throws UserException 
	 */
	@Override
	@Transactional
	public void active(String code) throws UserException{
		/**
		 * 1、使用code查询数据库，得到user
		 */
		User user = userDao.findByCode(code);
		/**
		 * 2、若是User不存在，则说明激活码出错
		 */
		if(user == null) throw new UserException("激活码无效!");
		/**
		 * 3、校验用户的状态是否为未激活状态，如果已经激活，说明是二次激活，抛出异常
		 */
		if(user.isState()) throw new UserException("您已经激活过了，请不要重新激活！");
		
		/**
		 * 4、修改用户状态
		 */
		userDao.updateState(user.getUid(), true);
		
	}
	
	/**
	 * 登陆功能
	 * @param form
	 * @return
	 * @throws UserException 
	 */
	@Override
	@Transactional
	public User login(User form) throws UserException{
		/*
		 * 1、使用username查询得到User
		 * 2、如果user为null,抛出异常（用户不存在）
		 * 3、比较密码，若不同抛出异常（密码错误）
		 * 4、查看用户的状态，若为false，抛出异常（您尚未激活）
		 * 5、返回User
		 */
		User user = userDao.findByUsername(form.getUsername());
		if(user == null) throw new UserException("用户账号不存在！");
		if(!user.getPassword().equalsIgnoreCase(form.getPassword()))
			throw new UserException("用户密码错误！");
		if(!user.isState())
			throw new UserException("用户尚未激活！");
		return user;
	}
}
