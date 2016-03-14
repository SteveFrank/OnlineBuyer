package com.ordering.order.dao;

import java.util.List;

import com.ordering.order.po.Order;
import com.ordering.order.po.OrderItem;

public interface OrderDao {
	public void addOrder(Order order);
	public void addOrderItemList(List<OrderItem> orderItemList);
	public List<Order> findByUid(String uid);
	void loadOrderItem(Order order);
	public Order load(String oid);
}
