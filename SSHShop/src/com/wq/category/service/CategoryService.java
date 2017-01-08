/**
 * 
 */
package com.wq.category.service;

import java.util.List;

import com.wq.category.dao.CategoryDao;
import com.wq.category.pojo.Category;

/**
 * @author youto8023
 *
 */
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * @return
	 *  查找所有一级分类
	 */
	public List<Category> findAll() {
		
		return categoryDao.findALl();
	}
	
	
}
