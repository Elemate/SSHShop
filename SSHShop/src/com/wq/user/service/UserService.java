/**
 * 
 */
package com.wq.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wq.user.dao.UserDao;
import com.wq.user.pojo.User;
import com.wq.utils.MailUtils;
import com.wq.utils.PageBean;
import com.wq.utils.UUIDUtils;

/**
 *<p>Title:</p>
 *<p>Description:</p> 
 *
 * @author 王庆
 * @date 2016年12月6日 下午5:32:04
 */
@Transactional
public class UserService {
	private UserDao userDao;
	
	/*
	 * ajax异步检测用户名
	*/
	public User findByName(String username){
		
		return userDao.findByName(username);
	}
	
	/**
	 * @param user
	 * 保存注册用户的代码
	 */
	public void save(User user) {
		// TODO Auto-generated method stub
		user.setState(0); 	//0代表未激活，1代表激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		//发送激活邮箱
		MailUtils.sendMail(user.getEmail(), user.getCode());
	}
	/**
	 * @param code 激活码
	 * @return
	 */
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param exitUser
	 * 更新用户状态
	 */
	public void update(User exitUser) {
		// TODO Auto-generated method stub
		userDao.update(exitUser);
	}

	/**
	 * 用户登录姓名密码验证
	 * @param username
	 * @param password
	 * @return
	 */
	public User userLogin(User user) {
		return userDao.userLogin(user);
	}

	/**
	 *  查询用户总记录数
	 * @param page
	 * @return
	 */
	public PageBean<User> findAll(int page) {
		// TODO Auto-generated method stub
		PageBean<User> pageBean = new PageBean<User>();
		int limit = 8;	//每页8条记录
		int totalCount;		//总记录数
		totalCount = userDao.findUserCount();	
		int totalPage = totalCount%limit==0 ? totalCount/limit : (totalCount/limit+1);		//总页数
		pageBean.setLimit(limit);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setPage(page);
		int begin = (page-1)*limit;
		List<User> list = userDao.findAll(begin,limit);
		pageBean.setList(list);
		return pageBean; 
	}

	/**
	 *  根据用户id查找用户实体
	 * @param uid
	 * @return
	 */
	public User findUser(Integer uid) {
		// TODO Auto-generated method stub
		return userDao.findUser(uid);
	}

	/**
	 * @param _user
	 */
	public void deleteUser(User _user) {
		// TODO Auto-generated method stub
		userDao.deleteUser(_user);
	}
	
}
