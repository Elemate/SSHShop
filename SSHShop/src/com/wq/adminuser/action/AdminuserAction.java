/**
 * 
 */
package com.wq.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.adminuser.pojo.Adminuser;
import com.wq.adminuser.service.AdminuserService;
import com.wq.user.pojo.User;
import com.wq.user.service.UserService;
import com.wq.utils.PageBean;

/**
 * @author youto8023
 * @date 2017年1月13日
 * @time 下午4:33:48
 *  后台管理Action
 */
public class AdminuserAction extends ActionSupport implements ModelDriven<Adminuser>{
	private AdminuserService adminuserService;
	private Adminuser adminuser = new Adminuser();
	private UserService userService;
	private int page;
	
	//进入后台页面
	public String adminPage(){
		return "adminPage";
	}
	//后台登录执行方法
	public String login(){
		Adminuser existAdminuser = adminuserService.login(adminuser);
		if (existAdminuser == null) {
			this.addActionError("用户名或者密码错误");
			return "loginFail";
		}
		ServletActionContext.getRequest().getSession().setAttribute("existAdminuser", existAdminuser);
		return "loginSuccess";
	}
	
	//查询所有用户
	public String findAll(){
		PageBean<User> pageBean = userService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	@Override
	public Adminuser getModel() {
		// TODO Auto-generated method stub
		return adminuser;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAdminuserService(AdminuserService adminuserService) {
		this.adminuserService = adminuserService;
	}

	public void setPage(int page) {
		this.page = page;
	}	
	
}
