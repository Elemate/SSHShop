package com.wq.user.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.user.pojo.User;
import com.wq.user.service.UserService;
/*
*/
/**
 *<p>Title:</p>
 *<p>Description:</p> 
 *
 * @author 王庆
 * @date 2016年12月4日 下午9:18:39
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private String checkcode;	//接受验证码
	private UserService userService;
	private User user = new User();
	
	/**
	 * 跳转到注册页面
	 */
	public String registPage(){
		return "registPage";
	}
	
	/*
	 * 注册检测用户名是否存在
	*/
	public String findByName() throws IOException{
		//判断用户名是否存在
		User exitUser = userService.findByName(user.getUsername());	
		HttpServletResponse response = ServletActionContext.getResponse();
		//这句要放在getWriter前面
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		if (exitUser!=null) {
			writer.println("<font color='red'>用户名已经存在</font>");
		} else {
			writer.println("<font color='green'>用户名可以使用</font>");
		}
		writer.flush();
		writer.close();
		return NONE;
	}
	
	/*
	 * 用户注册调用的方法
	*/
	public String regist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String TcheckCode = (String) request.getSession().getAttribute("checkCode");
		if (!TcheckCode.equalsIgnoreCase(checkcode)) {
			this.addActionError("验证码输入错误");
			return "checkCodeFail";
		}
		userService.save(user);
		//添加一些信息到action中去
		this.addActionMessage("注册成功，请去邮箱激活账户！");
		return "msg";
	}
	/*
	 * 用户激活时调用的方法
	 * 此处返回的model user只有一个属性code
	*/
	public String active(){
		//根据激活码查询用户是否存在
		User exitUser = userService.findByCode(user.getCode());
		if (exitUser==null) {
			//激活失败
			this.addActionMessage("激活失败：激活码错误!");
		} else {
			//激活成功
			exitUser.setState(1);
			exitUser.setCode(null);
			userService.update(exitUser);
			this.addActionMessage("激活成功，请速速去登录！");
		}
		return "msg";
	}
	
	/*
	 * 跳转到登录页面
	*/
	public String loginPage(){
		return "loginPage";
	}
	/*
	 * 用户登录的方法
	*/
	public String login(){
		User existUser = userService.userLogin(user);
		if (existUser!=null) {
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
		this.addActionMessage("用户名或密码错误");
		return LOGIN;
	}
	
	/*
	 * 用户退出登录
	*/
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the checkCode
	 */
	public String getCheckcode() {
		return checkcode;
	}

	/**
	 * @param checkCode the checkCode to set
	 */
	public void setCheckcode(String checkCode) {
		this.checkcode = checkCode;
	}
	
	
}
