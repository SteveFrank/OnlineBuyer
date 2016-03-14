package com.ordering.product.dao;

import java.util.List;

import com.ordering.product.po.Product;

public interface ProductDao {

	List<Product> findProductBySid(String sid);

	Product findProductByPid(String pid);

}
