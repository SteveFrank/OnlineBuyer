package com.ordering.product.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordering.product.dao.ProductDao;
import com.ordering.product.po.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
	@Autowired(required=true)
	@PersistenceContext(name="unitName")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findProductBySid(String sid) {
//		String jpal = "";
		String sql = "select * from product where s_pro_id = ?";
		List<Product> products = entityManager.createNativeQuery(sql,Product.class).setParameter(1, sid).getResultList();
		entityManager.close();
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Product findProductByPid(String pid) {
		String sql = "select * from product where pid = ?";
		List<Product> products = entityManager.createNativeQuery(sql,Product.class).setParameter(1, pid).getResultList();
		entityManager.close();
		return products.get(0);
	}
}
