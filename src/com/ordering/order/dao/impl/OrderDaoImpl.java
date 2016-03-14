package com.ordering.order.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordering.order.dao.OrderDao;
import com.ordering.order.po.Order;
import com.ordering.order.po.OrderItem;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
	@Autowired(required=true)
	@PersistenceContext(name="unitName")
	private EntityManager entityManager;

	@Override
	public void addOrder(Order order) {
//		String sql = "insert into orders values(?,?,?,?,?,?)";
		/**
		 * 处理util的Date转换成sql的Timestamp
		 */
//		Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
//		Object[] params = {order.getOid(),order.getOrdertime(),
//				order.getTotal(),order.getState(),order.getOwner().getUid(),order.getAddress()};
		entityManager.persist(order);
		entityManager.close();
	}
	
	/**
	 * 添加订单条目
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList){
		/**
		 * 有多个一维数组组成的批处理使用
		 * 每个一维数组与sql执行一次，多个以为数组执行多次   
		 */
		
//		String sql = "insert into orderitem values(?,?,?,?,?)";
		/**
		 * 将orderItemList转换成二维数组
		 * 	>把一个OrderItem对象转换成一个以为数组
		 */
//		Object[][] params = new Object[orderItemList.size()][];
		//循环遍历OrderItemList，使用每个orderItem对象为params中每个一维数组赋值
		for(int i = 0;i < orderItemList.size();i++){
			OrderItem item = orderItemList.get(i);
			entityManager.persist(item);
//			params[i] = new Object[]{item.getIid(), item.getCount(),
//				item.getSubtotal(),item.getOrder().getOid(),
//				item.getProduct().getPid()};
		}
		
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByUid(String uid) {
//		String jpal = "select order from com.ordering.order.po.Order order where order.owner.uid=:uid";
//		List<Order> orderList = entityManager.createQuery(jpal).setParameter("uid", uid).getResultList();
		/*
		 * 1、得到所有的订单
		 */
		String sql = "select * from orders where o_u_id=?";
		List<Order> orderList = entityManager.createNativeQuery(sql,Order.class).setParameter(1, uid).getResultList();
		/*
		 * 2、循环遍历每个order,为其加载它自己所有的订单条目
		 */
		for(Order order : orderList) {
			//为order对象添加它的所有订单条目
			loadOrderItem(order);
		}
		entityManager.close();
		return orderList;
	}
	
	/**
	 * 加载指定的订单的所有的订单条目的能力
	 * @param order
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void loadOrderItem(Order order) {
		// TODO Auto-generated method stub
		String sql = "select * from orderitem where o_item_id=?";
		List<OrderItem> orderItemsList = 
				entityManager.createNativeQuery(sql,OrderItem.class).setParameter(1, order.getOid()).getResultList();
		order.setOrderItemsList(orderItemsList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order load(String oid) {
		String sql = "select * from orders where oid = ?";
		List<Order> orderList = entityManager.createNativeQuery(sql,Order.class).setParameter(1, oid).getResultList();
		String _sql = "select * from orderitem where o_item_id=?";
		List<OrderItem> orderItemsList = 
				entityManager.createNativeQuery(_sql,OrderItem.class).setParameter(1, orderList.get(0).getOid()).getResultList();
		Order order = orderList.get(0);
		order.setOrderItemsList(orderItemsList);
		return order;
	}
	
}
