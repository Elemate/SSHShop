/**
 * 
 */
package com.wq.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wq.adminuser.pojo.Adminuser;

/**
 * @author youto8023
 * @date 2017年1月13日
 * @time 下午4:42:54
 */
public class AdminuserDao extends HibernateDaoSupport {

	/**
	 * @param adminuser
	 * @return
	 */
	public Adminuser login(Adminuser adminuser) {
		String hql = "from Adminuser a where a.username = ? and a.password = ?";
		List<Adminuser> list = this.getHibernateTemplate().find(hql, adminuser.getUsername(),adminuser.getPassword());
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
