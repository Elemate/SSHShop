/**
 * 
 */
package com.wq.adminuser.action;

import java.util.List;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.category.pojo.Category;
import com.wq.category.service.CategoryService;

/**
 * @author youto8023
 * @date 2017年1月15日
 * @time 下午3:42:13
 *  一级分类管理Action
 */
public class CategoryadminAction extends ActionSupport implements ModelDriven<Category>{
	private CategoryService categoryService;
	private Category category = new Category();
	
	//查询所用一级分类
	public String findAll(){
		List<Category> list = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "findAll";
	}
	
	//编辑一级分类
	public String edit(){
		category = categoryService.findCategory(category.getCid());
		return "edit";
	}
	
	//删除指定一级分类
	public String delete(){
		category = categoryService.findCategory(category.getCid());
		categoryService.delete(category);
		return "delete";
	}
	
	//新增一级分类实体
	public String save(){
		categoryService.save(category);
		return "save";
	}
	
	//更新一级分类实体
	public String update(){
		categoryService.update(category);
		return "update";
	}
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
