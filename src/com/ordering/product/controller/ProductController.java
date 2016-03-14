package com.ordering.product.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ordering.product.po.Product;
import com.ordering.product.service.ProductService;

@Controller
public class ProductController {
	
	@Resource(name="productService")
	private ProductService productService;
	
	@RequestMapping(value="/showProductBySid")
	public String showProductBySid(HttpServletRequest request, HttpServletResponse response){
		String sid = request.getParameter("sid");
		List<Product> products = productService.findProductBySid(sid);
		request.setAttribute("productList", products);
		return "jsps/product/list";
	}
	
	@RequestMapping(value="/showProductDesc")
	public String showProductDesc(HttpServletRequest request, HttpServletResponse response){
		String pid = request.getParameter("pid");
		Product product = productService.findProductByPid(pid);
		request.setAttribute("product", product);
		return "jsps/product/desc";
	}
}
