package com.ordering.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordering.product.dao.ProductDao;
import com.ordering.product.po.Product;
import com.ordering.product.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Resource(name="productDao")
	private ProductDao productDao;
	
	@Transactional
	@Override
	public List<Product> findProductBySid(String sid) {
		return productDao.findProductBySid(sid);
	}
	
	@Transactional
	@Override
	public Product findProductByPid(String pid) {
		return productDao.findProductByPid(pid);
	}
}
