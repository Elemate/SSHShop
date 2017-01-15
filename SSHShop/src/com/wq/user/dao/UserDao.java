/**
 * 
 */
package com.wq.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wq.user.pojo.User;
import com.wq.utils.PageBean;
import com.wq.utils.PageHibernateCallback;

/**
 *<p>Title:</p>
 *<p>Description:</p> 
 *
 * @author 王庆
 * @date 2016年12月6日 下午5:31:52
 */
public class UserDao extends HibernateDaoSupport {

	/*
	 * ajax检测用户名
	*/
	public User findByName(String username){
		
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * @param user
	 * 将用户信息保存到数据库
	 */
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	/**
	 * @param code 激活码
	 * @return
	 * 跟据激活码查询用户是否存在
	 */
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		String hql = "from User where code =?";
		List<User> list = this.getHibernateTemplate().find(hql, code);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @param exitUser
	 * 更新用户状态
	 */
	public void update(User exitUser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(exitUser);
	}

	/**
	 * 用户登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	public User userLogin(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 *  查找所有注册用户
	 * @param page
	 * @return
	 */
	public int findUserCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list!=null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * @param begin
	 * @param limit
	 * @return
	 */
	public List<User> findAll(int begin, int limit) {
		String hql = "from User";
		List<User> list = this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, begin, limit));
		if (list!=null && list.size()>0) {
			return list;
		}
		return null;
	}

	/**
	 * @param uid
	 * @return
	 */
	public User findUser(Integer uid) {
		String hql = "from User where uid = ?";
		List<User> list = this.getHibernateTemplate().find(hql, uid);
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @param _user
	 */
	public void deleteUser(User _user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(_user);
	}



	
	/*
	 * 使用hibernate原生框架查询
	 * 由于Hiberante的配置文件hibernate.cfg.xml交给了spring拖管，这种方式不可以查询
	*/	
	/*public User findByName1(String username){
		String sql = "select * from user where username ="+username;
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		List<User> list = null;
		try {
			list = session.createSQLQuery(sql).addEntity(User.class).list();
			tran.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}*/
}
