/**
 * 
 */
package com.wq.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wq.category.pojo.Category;


/**
 * @author youto8023
 * 一级分类dao层业务
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * @return
	 * 查询所有一级分类
	 */
	public List<Category> findALl() {
		String hql = "from Category ";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
