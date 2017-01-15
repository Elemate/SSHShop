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

	/** 
	 *  查询指定一级分类
	 * @param cid
	 * @return
	 */
	public Category findCategory(Integer cid) {
		String hql = "from Category where cid = ?";
		List<Category> list = this.getHibernateTemplate().find(hql, cid);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 *  删除指定一级分类
	 * @param category
	 */
	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
	}

	/**
	 *  新增一级分类实体
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	/**
	 * 更新一级分类实体
	 * @param category
	 */
	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
	}

}
