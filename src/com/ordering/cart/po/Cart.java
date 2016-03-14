package com.ordering.cart.po;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车类
 * @author 杨谦
 * @date 2015-9-22 上午11:11:54
 *
 */
public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	/**
	 * 计算出合计
	 * 要注意使用大数据的类型防止误差出现
	 * @return
	 */
	public double getTotal(){
		//计算出所有的小计之和
		BigDecimal total = new BigDecimal("0");
		
		for(CartItem cartItem : map.values()){
			BigDecimal subtotal = new BigDecimal(cartItem.getSubtotal()+"");
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}
	/**
	 * 添加条目到车中
	 * @param cartItem
	 */
	public void add(CartItem cartItem){
		if(map.containsKey(cartItem.getProduct().getPid())){
			CartItem _cartItem = map.get(cartItem.getProduct().getPid());
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			map.put(cartItem.getProduct().getPid(), _cartItem);
		} else {
			map.put(cartItem.getProduct().getPid(), cartItem);
		}
	}
	/**
	 * 清空所有条目
	 */
	public void clear() {
		map.clear();
	}
	/**
	 * 删除指定条目
	 * @param bid
	 */
	public void delete(String bid) {
		map.remove(bid);
	}
	/**
	 * 获取所有条目
	 * @return
	 */
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
