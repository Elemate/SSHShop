/**
 * 
 */
package com.wq.utils;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author youto8023
 *	用来分页查询代码
 * @param <T>
 */
public class PageHibernateCallback<T> implements HibernateCallback<List<T>>{
	private String hql;
	private Object[] params;
	private int startIndex;
	private int pageSize;
	
	public PageHibernateCallback(String hql, int startIndex, int pageSize, Object ... params) {
		super();
		this.hql = hql;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
		this.params = params;
	}
	
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException,
			SQLException {
		Query query = session.createQuery(hql);
		if (params!=null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

}
