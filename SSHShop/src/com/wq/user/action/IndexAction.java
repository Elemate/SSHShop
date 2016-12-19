package com.wq.user.action;


import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wq.category.pojo.Category;
import com.wq.category.service.CategoryService;
import com.wq.product.pojo.Product;
import com.wq.product.service.ProductService;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;

	public String execute() {
		
		//查找所有一级分类
		List<Category> clist = categoryService.findAll();
		ActionContext.getContext().getSession().put("clist", clist);
		
		//查询热门商品
		List<Product> hlist = productService.findHot();
		//保存到值栈中区
		ActionContext.getContext().getValueStack().set("hlist", hlist);
		
		//查询最新商品
		List<Product> nlist = productService.findNew();
		ActionContext.getContext().getValueStack().set("nlist", nlist);
		
		return "index";
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	
}
