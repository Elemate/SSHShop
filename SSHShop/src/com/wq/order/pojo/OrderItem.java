/**
 * 
 */
package com.wq.order.pojo;

import com.wq.product.pojo.Product;

/**
 * @author youto8023
 * 订单项实体类
 */
public class OrderItem {
	private Integer itemid;
	private Integer count;
	private double subTotal;
	private Order order;
	private Product product;
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
