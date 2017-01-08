/**
 * 
 */
package com.wq.cart.pojo;

import java.io.Serializable;

import com.wq.product.pojo.Product;

/**
 * @author youto8023
 * 购物项的是实体
 */
public class CartItem {
	//商品对象
	private Product product;
	//商品数量
	private Integer count;
	//小计
	private Double subCount;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubCount() {
		return count*product.getShop_price();
	}
	/*public void setSubCount(Double subCount) {
		this.subCount = subCount;
	}*/
	
}
