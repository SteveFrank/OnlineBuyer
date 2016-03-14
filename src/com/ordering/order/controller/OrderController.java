package com.ordering.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ordering.cart.po.Cart;
import com.ordering.cart.po.CartItem;
import com.ordering.order.po.Order;
import com.ordering.order.po.OrderItem;
import com.ordering.order.service.OrderService;
import com.ordering.user.po.User;
import com.ordering.utils.CommonUtils;

@Controller
public class OrderController {
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	/**
	 * 添加订单
	 * 把session中的车用来生成Order对象
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addOrder")
	public String addOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Cart --> Order
		 * 1、从session中获取得到cart
		 * 2、使用cart生成Order对象
		 * 3、调用service的方法添加订单
		 * 4、保存order到request域中，转发到/jsps/order/desc.jsp
		 */
		//从session中获取cart
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//设置编号
		order.setOrdertime(new Date());//设置当前系统时间
		order.setState(1);//设置当前状态是未付款状态
		User user = (User)request.getSession().getAttribute("session_user");
		order.setOwner(user);
		order.setTotal(cart.getTotal());
		
		//创建订单条目集合
		List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
		//循环遍历Cart中所有CartItem，使用每一个CartItem对象添加到集合中
		for(CartItem cartItem : cart.getCartItems()) {
			OrderItem oi = new OrderItem();//创建订单条目
			oi.setIid(CommonUtils.uuid());
			oi.setCount(cartItem.getCount());
			oi.setProduct(cartItem.getProduct());
			oi.setSubtotal(cartItem.getSubtotal());
			oi.setOrder(order);
			orderItemsList.add(oi);
		}
		
		//把所有的订单条目添加到订单中
		order.setOrderItemsList(orderItemsList);
		order.setAddress(user.getAddress());
		//情况购物车
		cart.clear();
		orderService.addOrder(order);
		request.setAttribute("order", order);
		return "jsps/order/desc";
	}
	
	@RequestMapping(value="/myOrders")
	public String myOrders(HttpServletRequest request, HttpServletResponse response){
		/*
		 * 1、从session得到当前的用户，再获取其他的uid
		 * 2、使用uid调用OrderService#myOrders(uid)得到该用户的所有订单List<Order>
		 * 3、把订单表保存到request域中，转发到/jsps/order/list.jsp
		 */
		User user = (User) request.getSession().getAttribute("session_user");
		List<Order> orderList = orderService.myOrders(user.getUid());
		request.setAttribute("orderList", orderList);
		return "jsps/order/list";
	}
	
	@RequestMapping(value="/load")
	public String load(HttpServletRequest request, HttpServletResponse response) {
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		return "jsps/order/desc";
	}
}
