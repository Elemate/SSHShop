/**
 * 
 */
package com.wq.product.dao;

import java.util.List;











import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wq.product.pojo.Product;
import com.wq.utils.HibernateSessionFactory;
import com.wq.utils.PageHibernateCallback;

/**
 * @author youto8023
 *	商品的持久层代码
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * @return
	 * 首页查询所有热门商品
	 * hibernateDemo有原生sql写法
	 */
	public List<Product> findHot() {
		//离线条件查询方法
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门商品条件是1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序查询
		criteria.addOrder(Order.desc("pdate"));
		//执行查询条件
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * @return
	 * 首页显示最新商品
	 */
	public List<Product> findNew() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * @param pid
	 * @return
	 * 查询商品详情页面
	 */
	public Product findDetail(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * @param cid
	 * @return
	 * 	查询所属一级分类商品总条目数
	 */
	public int findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param cid 一级分类id
	 * @param page	当前页数
	 * @return
	 * 	查询一级分类下分页商品
	 */
	public List<Product> findProductByCid(Integer cid, Integer begin, Integer limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, begin, limit, cid));
		if (list!=null && list.size()>0) {
			return list;
		}
		return null;
	}

	/**
	 * @param csid
	 * @return
	 * 查找所属二级分类商品数目
	 */
	public int findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param csid
	 * @param begin
	 * @param limit
	 * @return
	 *  查找二级分类下分页商品
	 */
	public List<Product> findProductByCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().executeFind(new PageHibernateCallback<Product>(hql, begin, limit, csid));
		if (list!=null && list.size()>0) {
			return list;
		}
		return null;
	}
	

	
}
