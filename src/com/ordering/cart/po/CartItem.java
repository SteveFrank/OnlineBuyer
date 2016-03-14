package com.ordering.cart.po;

import java.math.BigDecimal;

import com.ordering.product.po.Product;

/**
 * 购物条目
 * @author 杨谦
 * @date 2015-9-22 上午11:13:00
 *
 */
public class CartItem {
	
	private Product product;//商品
	private int count;//数量
	
	public double getSubtotal(){//小计方法，是没有成员变量的
		BigDecimal b1 = new BigDecimal(product.getPrice() + "");
		BigDecimal b2 = new BigDecimal(count + "");
		return b1.multiply(b2).doubleValue();
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", count=" + count + "]";
	}
	
}
