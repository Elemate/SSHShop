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

	/**
	 *  查询指定一级分类
	 * @param cid
	 * @return
	 */
	public Category findCategory(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findCategory(cid);
	}

	/**
	 * 删除指定一级分类实体
	 * @param category
	 */
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryDao.delete(category);
	}

	/**
	 *  新增一级分类实体
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	/**
	 * 更新一级分类实体
	 * @param category
	 */
	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
	
	
}
