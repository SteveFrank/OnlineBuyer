package com.ordering.category.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ordering.category.dao.CategoryDao;
import com.ordering.category.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	
	@Resource(name="categoryDao")
	private CategoryDao categoryDao;
}
