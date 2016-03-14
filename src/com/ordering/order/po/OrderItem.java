package com.ordering.order.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ordering.product.po.Product;

/**
 * 订单条目类
 * @author 杨谦
 * @date 2015-9-22 下午3:31:27
 *
 */
@Entity
@Table(name="orderitem")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length=50,nullable=false)
	private String iid;
	@Column(name="count")
	private int count;//总数量
	@Column(name="subtotal")
	private double subtotal;//小计
	
	@ManyToOne(cascade=CascadeType.REFRESH,targetEntity=Order.class)
	@JoinColumn(name="item_o_id")
	private Order order;//所属订单
	@ManyToOne(optional=false,cascade=CascadeType.REFRESH,targetEntity=Product.class)
	@JoinColumn(name="o_pro_id")
	private Product product;//所购买的菜品

	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal="
				+ subtotal + ", order=" + order + ", product=" + product + "]";
	}
	
}
