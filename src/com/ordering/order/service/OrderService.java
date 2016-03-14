package com.ordering.order.service;

import java.util.List;

import com.ordering.order.po.Order;

public interface OrderService {

	public void addOrder(Order order);
	
	public List<Order> myOrders(String uid);

	public Order load(String oid);

}
