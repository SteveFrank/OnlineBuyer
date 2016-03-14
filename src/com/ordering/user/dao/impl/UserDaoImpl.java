package com.ordering.user.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordering.user.dao.UserDao;
import com.ordering.user.po.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired(required=true)
	@PersistenceContext(name="unitName")
	private EntityManager entityManager;
	
	/**
	 * 按照用户名查询
	 * @param username
	 * @return
	 */
	@Override
	public User findByUsername(String username){
		String jpal = "select user from com.ordering.user.po.User user where user.username=:username";
		List<User> list_user = entityManager.createQuery(jpal).setParameter("username", username).getResultList();
		if(list_user.isEmpty()) {
			return null;
		} else {
			return list_user.get(0);
		}
	}
	/**
	 * 按照邮箱信息查询
	 * @param email
	 * @return
	 */
	@Override
	public User findByEmail(String email){
		String jpal = "select user from com.ordering.user.po.User user where user.email=:email";
		List<User> list_user = entityManager.createQuery(jpal).setParameter("email", email).getResultList();
		if(list_user.isEmpty()) {
			return null;
		} else {
			return list_user.get(0);
		}
	}
	/**
	 * 按照激活码
	 * @param username
	 * @return
	 */
	@Override
	public User findByCode(String code){
		String jpal = "select user from com.ordering.user.po.User user where user.code=:code";
		List<User> list_user = entityManager.createQuery(jpal).setParameter("code", code).getResultList();
		if(list_user.isEmpty()) {
			return null;
		} else {
			return list_user.get(0);
		}
	}
	/**
	 * 修改指定用户的指定状态
	 */
	@Override
	public void updateState(String uid,boolean state){
		User user = (User) entityManager.find(User.class, uid);
		user.setState(state);
		entityManager.close();
	}
	/**
	 * 实现用户注册的添加功能
	 * @param user
	 */
	@Override
	public void add(User user){
		entityManager.persist(user);
		entityManager.close();
	}

}
