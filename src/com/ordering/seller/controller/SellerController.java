package com.ordering.seller.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ordering.seller.po.Seller;
import com.ordering.seller.service.SellerService;

@Controller
public class SellerController {
	
	@Resource(name="sellerService")
	private SellerService sellerService;
	
	@RequestMapping(value="showSellerAll")
	public String showSellerAllName(HttpServletRequest request, HttpServletResponse response) {
		List<Seller> seller_list = sellerService.queryAllSeller();
		request.setAttribute("sellerList", seller_list);
		return "jsps/leftShowSeller";
	}
}
