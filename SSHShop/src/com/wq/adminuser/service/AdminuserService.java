/**
 * 
 */
package com.wq.adminuser.service;

import com.wq.adminuser.dao.AdminuserDao;
import com.wq.adminuser.pojo.Adminuser;

/**
 * @author youto8023
 * @date 2017年1月13日
 * @time 下午4:43:26
 */
public class AdminuserService {
	private AdminuserDao adminuserDao;

	public void setAdminuserDao(AdminuserDao adminuserDao) {
		this.adminuserDao = adminuserDao;
	}

	/**
	 * @param adminuser
	 * @return
	 */
	public Adminuser login(Adminuser adminuser) {
		// TODO Auto-generated method stub
		return adminuserDao.login(adminuser);
	}
	
}
