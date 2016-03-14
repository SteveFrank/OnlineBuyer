package com.ordering.cart.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ordering.cart.po.Cart;
import com.ordering.cart.po.CartItem;
import com.ordering.product.po.Product;
import com.ordering.product.service.ProductService;

@Controller
public class CartController {
	
	@Resource(name="productService")
	private ProductService productService;
	/**
	 * 添加购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/addCart")
	public String addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1、获取车
		 * 2、得到购物条目（得到图书和数量）
		 */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			request.setAttribute("mag", "请登录后添加购物车");
			return "jsps/user/login";
		}else{
			//通过PID查找商品
			String pid = request.getParameter("pid");
			System.out.println("======="+pid+"======");
			Product product = productService.findProductByPid(pid);
			System.out.println("======="+product+"======");
			//用户添加的数目
			int count = Integer.parseInt(request.getParameter("count"));
			System.out.println("======="+count+"======");
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCount(count);
			
			System.out.println(cartItem);
			cart.add(cartItem);
			
			request.getSession().setAttribute("cart", cart);
//			request.setAttribute("cart", cart);
			return "jsps/cart/list";
		}
	}
	/**
	 * 清空购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/clearCart")
	public String clearCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			request.setAttribute("mag", "请登录后清空购物车");
			return "jsps/user/login";
		}else{
			cart.clear();
			request.getSession().setAttribute("cart", cart);
			return "jsps/cart/list";
		}
	}
	/**
	 * 删除购物车中的指定条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteProductByPid")
	public String deleteCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			request.setAttribute("mag", "请登录后清空购物车");
			return "jsps/user/login";
		}else{
			String pid = request.getParameter("pid");
			cart.delete(pid);
			request.getSession().setAttribute("cart", cart);
			return "jsps/cart/list";
		}
	}
}
