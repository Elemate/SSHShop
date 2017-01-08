/**
 * 
 */
package com.wq.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wq.order.pojo.Order;
import com.wq.order.pojo.OrderItem;
import com.wq.utils.PageHibernateCallback;

/**
 * @author youto8023
 * 订单管理Dao层
 */
public class OrderDao extends HibernateDaoSupport {

	/**
	 * @param order
	 * 	保存订单
	 */
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}

	/**
	 * @param uid
	 * @return
	 *  查找用户订单总记录数
	 */
	public int findOrderCount(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param limit	每页数目
	 * @param begin 起始页数
	 * @param uid	用户Id
	 * @return
	 * 	查找该用户所用订单实体
	 */
	public List<Order> findOrderByUid(int limit, int begin, Integer uid) {
		String hql = " from Order o where o.user.uid =? order by o.oid Desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, begin, limit, uid));
		if (list!=null && list.size()>0) {
			return list;
		}
		return null;
	}

	/**
	 * @param itemid
	 *  删除订单项功能
	 */
	public  void removeOrderItem(Integer itemid) {
		String hql_order = "from OrderItem oi where oi.itemid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql_order, itemid);
		OrderItem orderItem = list.get(0);
		this.getHibernateTemplate().delete(orderItem);
	}

	/**
	 * @param oid
	 * @return
	 *  根据订单号查找订单
	 */
	public Order findOrderByOid(Integer oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * @param order
	 */
	public void update(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(order);
	}

}
