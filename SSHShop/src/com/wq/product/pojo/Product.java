/**
 * 
 */
package com.wq.product.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.wq.categorySecond.pojo.CategorySecond;
import com.wq.order.pojo.OrderItem;

/**
 * @author youto8023
 *	商品实体类
 */
public class Product implements Serializable{
	private Integer pid;
	private String pname;
	private Double market_price;
	private Double shop_price;
	private String image;
	private String pdate;
	private Integer is_hot;
	private String pdesc;
	private CategorySecond categorySecond;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	public Integer getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the market_price
	 */
	public Double getMarket_price() {
		return market_price;
	}
	/**
	 * @param market_price the market_price to set
	 */
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	/**
	 * @return the shop_price
	 */
	public Double getShop_price() {
		return shop_price;
	}
	/**
	 * @param shop_price the shop_price to set
	 */
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the pdate
	 */
	public String getPdate() {
		return pdate;
	}
	/**
	 * @param pdate the pdate to set
	 */
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	/**
	 * @return the is_hot
	 */
	public Integer getIs_hot() {
		return is_hot;
	}
	/**
	 * @param is_hot the is_hot to set
	 */
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	/**
	 * @return the pdesc
	 */
	public String getPdesc() {
		return pdesc;
	}
	/**
	 * @param pdesc the pdesc to set
	 */
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
