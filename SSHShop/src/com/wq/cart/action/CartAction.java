/**
 * 
 */
package com.wq.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wq.cart.pojo.Cart;
import com.wq.cart.pojo.CartItem;
import com.wq.product.service.ProductService;

/**
 * @author youto8023
 * 购物车的Action
 */
public class CartAction extends ActionSupport {
	private Integer pid;
	private Integer count;
	private ProductService productService;
	
	//添加购物项的方法
	public String addCart(){
		Cart cart = getCart();
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findDetail(pid));
		cart.addCartItem(cartItem);
		return "addCart";
	}
	public String myCart(){
		return "myCart";
	}
	
	//清空购物车的方法
	public String clearCart(){
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	//删除购物项的方法
	public String removeCartItem(){
		Cart cart = getCart();
		cart.removeCartItem(pid);
		return "removeCartItem";
	}
	
	//获得购物车对象
	private Cart getCart(){
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart==null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		
		return cart;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
}
