package com.ordering.category.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordering.category.dao.CategoryDao;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	@Autowired(required=true)
	@PersistenceContext(name="unitName")
	private EntityManager entityManager;
}
