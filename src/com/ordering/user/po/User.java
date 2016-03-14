package com.ordering.user.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 杨谦
 *
 */
@Entity
@Table(name="user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 对应数据库中的User的相关属性
	 */
	@Id
	@Column(length=50,nullable=false)
	private String uid;//主键
	@Column(length=50,nullable=false)
	private String username;//用户名
	@Column(length=50,nullable=false)
	private String password;//密码
	@Column(length=50,nullable=false)
	private String email;//邮箱
	@Column(length=100,nullable=false)
	private String code;//用户激活码
	@Column(length=10,nullable=false)
	private boolean state;//用户激活状态
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password="
				+ password + ", email=" + email + ", code=" + code + ", state="
				+ state + "]";
	}
	
}