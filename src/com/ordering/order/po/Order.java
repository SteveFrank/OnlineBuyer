package com.ordering.order.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ordering.user.po.User;

/**
 * 订单类
 * @author 杨谦
 * @date 2015-9-22 下午3:31:18
 *
 */
@Entity
@Table(name="orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length=50,nullable=false)
	private String oid;
	@Column(name="ordertime")
	private Date ordertime;//下单时间
	@Column(name="total")
	private double total;//合计
	/*
	 * state对应的数据字典
	 * 1==未付款
	 * 2==已经付款没有发货
	 * 3==已经发货但是没有收获确认
	 * 4==已经确认交易成功
	 */
	@Column(name="state")
	private int state;//当前订单的状态
	@ManyToOne(cascade=CascadeType.ALL,targetEntity=User.class)
	@JoinColumn(name="o_u_id")
	private User owner;
	@Column(length=50,nullable=false)
	private String address;//收获地址
	//体现出表之间的关系
	@OneToMany(cascade=CascadeType.ALL,targetEntity=OrderItem.class)
	@JoinColumn(name="o_item_id")
	private List<OrderItem> orderItemsList;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<OrderItem> getOrderItemsList() {
		return orderItemsList;
	}
	public void setOrderItemsList(List<OrderItem> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	
}
