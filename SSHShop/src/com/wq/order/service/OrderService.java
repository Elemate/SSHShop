/**
 * 
 */
package com.wq.order.service;

import java.util.List;

import com.wq.order.dao.OrderDao;
import com.wq.order.pojo.Order;
import com.wq.utils.PageBean;

/**
 * @author youto8023
 *	订单管理业务层
 */
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * @param order
	 * 保存订单
	 */
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}

	/**
	 * @param uid
	 * @return
	 * 根据用户Id获得其所有的订单
	 */
	public PageBean<Order> findOrderByUid(Integer uid, Integer page) { 
		PageBean<Order> pageBean = new PageBean<Order>();
		int limit = 5;	//每页显示数目
		int totalCount = orderDao.findOrderCount(uid);	//总记录数
		int totalPage = 1;	//总页数
		if (totalCount%limit==0) {
			totalPage = totalCount/limit;
		} else {
			totalPage = totalCount/limit + 1;
		}
		int begin = (page-1)*limit;
		List<Order> orders = orderDao.findOrderByUid(limit, begin, uid);
		for (Order order : orders) {
			String orderTime = order.getOrdertime();
			
//			String[] orderTimes = orderTime.split("\\.");
			order.setOrdertime(orderTime.substring(0, orderTime.length()-2));
		}
		pageBean.setList(orders);
		pageBean.setTotalCount(totalCount);
		pageBean.setPage(page);
		pageBean.setTotalPage(totalPage);
		pageBean.setLimit(limit);
		return pageBean;
	}

	/**
	 * @param itemid
	 * 	删除订单项功能
	 */
	public void removeOrderItem(Integer itemid) {
		// TODO Auto-generated method stub
		orderDao.removeOrderItem(itemid);
	}

	/**
	 * @param oid
	 * @return
	 * 根据订单号查找订单实例
	 */
	public Order findOrderByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByOid(oid);
	}

	/**
	 * @param order
	 */
	public void update(Order order) {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}
	
}
