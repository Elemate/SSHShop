/**
 * 
 */
package com.wq.product.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wq.product.pojo.Product;
import com.wq.product.service.ProductService;
import com.wq.utils.PageBean;

/**
 * @author youto8023
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private ProductService productService;
	Product product = new Product();
	//一级分类id
	private Integer cid;
	//首次进入的页数
	private Integer page; 
	//二级分类id
	private Integer csid;

	/*
	 * 查询商品详情页面
	*/
	public String findDetail(){
		product = productService.findDetail(product.getPid());
		
		return "detail";
	}
	
	/*
	 * 根据一级分类id查询所属二级分类，以及所属一级分类商品
	*/
	public String findByCid(){
		//获得分页对象
		PageBean<Product> pageBean = productService.findProductByCid(cid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	/*
	 * 根据二级分类查找对应的商品
	*/
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	@Override
	public Product getModel() {
		
		return product;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
}
