package com.ordering.product.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ordering.category.po.Category;

@Entity
@Table(name="product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length=50,nullable=false)
	private String pid;
	@Column(length=50,nullable=false)
	private String pname;
	@Column(name="price")
	private double price;
	@Column(length=50,nullable=false)
	private String describle;
	@Column(length=150,nullable=false)
	private String image;
	
	@ManyToOne(cascade=CascadeType.REFRESH,targetEntity=Category.class)
	@JoinColumn(name="pro_c_id")
	private Category category;//外键关联在bean中的写法与实现方法

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescrible() {
		return describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price
				+ ", describle=" + describle + ", image=" + image
				+ ", category=" + category + "]";
	}
	
}
