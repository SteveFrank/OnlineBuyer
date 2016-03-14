package com.ordering.product.service;

import java.util.List;

import com.ordering.product.po.Product;

public interface ProductService {

	List<Product> findProductBySid(String sid);

	Product findProductByPid(String pid);

}
