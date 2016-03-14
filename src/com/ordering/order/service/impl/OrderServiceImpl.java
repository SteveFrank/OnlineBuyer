package com.ordering.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordering.order.dao.OrderDao;
import com.ordering.order.po.Order;
import com.ordering.order.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	@Transactional
	@Override
	public void addOrder(Order order) {
		orderDao.addOrder(order);
		orderDao.addOrderItemList(order.getOrderItemsList());
	}
	
	@Transactional
	@Override
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}
	
	@Transactional
	@Override
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
}
