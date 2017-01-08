/**
 * 
 */
package com.wq.utils;

import java.util.List;

/**
 * @author youto8023
 *	分页实体类
 */
public class PageBean<T> {
	private int page;	//当前所在页数
	private int limit;	//每页数目
	private int totalPage;	//总页数
	private int totalCount;	//总数目
	private List<T> list;	//每页显示数据集合
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
