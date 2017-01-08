/**
 * 
 */
package com.wq.category.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.wq.categorySecond.pojo.CategorySecond;

/**
 * @author youto8023
 *
 */
public class Category implements Serializable{
	private Integer cid;
	private String cname;
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * @return the categorySeconds
	 */
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	/**
	 * @param categorySeconds the categorySeconds to set
	 */
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	
}
