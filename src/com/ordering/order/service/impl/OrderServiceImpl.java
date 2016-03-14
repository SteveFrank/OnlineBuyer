package com.ordering.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ordering.order.dao.OrderDao;
import com.ordering.order.po.Order;
import com.ordering.order.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	@Override
	public void addOrder(Order order) {
		
	}
	
}
