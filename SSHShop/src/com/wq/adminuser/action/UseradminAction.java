/**
 * 
 */
package com.wq.adminuser.action;

import org.hibernate.sql.Delete;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.user.pojo.User;
import com.wq.user.service.UserService;

/**
 * @author youto8023
 * @date 2017年1月14日
 * @time 下午11:33:40
 */
public class UseradminAction extends ActionSupport implements ModelDriven<User> {
	private UserService userService;
	private User user = new User();
	
	//删除用户
	public String delete(){
		User _user = userService.findUser(user.getUid());
		if (_user!=null) {
			userService.deleteUser(_user);
			return "delete";
		}
		return NONE;
	}
	
	//编辑用户
	public String edit(){
		user = userService.findUser(user.getUid());
		return "edit";
		
	}
	//更新用户信息
	public String update(){
		userService.update(user);
		return "update";
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
