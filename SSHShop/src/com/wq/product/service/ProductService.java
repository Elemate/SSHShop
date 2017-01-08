/**
 * 
 */
package com.wq.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wq.product.dao.ProductDao;
import com.wq.product.pojo.Product;
import com.wq.utils.PageBean;

/**
 * @author youto8023
 * 商品的业务层代码
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	/**
	 * @param productDao the productDao to set
	 */
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * @return
	 * 查询所有热门商品
	 */
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	/**
	 * @return
	 * 首页显示最新商品
	 */
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	/**
	 * @param pid 商品Id
	 * @return
	 * 查询商品详情页面
	 */
	public Product findDetail(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findDetail(pid);
	}

	/**
	 * @param cid 一级分类id
	 * @param page	首次进入页数
	 * @return pageBean对象
	 * 查询一级分类所属商品
	 */
	public PageBean findProductByCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int totalCount = 0; //总数目
		int totalPage = 0; //总页数
		int limit = 8;
		totalCount = productDao.findCountByCid(cid);
		if (totalCount%limit==0) {
			totalPage = totalCount/limit;
		} else {
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalCount(totalCount);
		pageBean.setLimit(limit);
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit; 	//开始查询位置
		List<Product> list = productDao.findProductByCid(cid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	/**
	 * @param csid
	 * @param page
	 * @return
	 * 根据二级分查找对应的商品
	 */
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int totalCount = 0; //总数目
		int totalPage = 0; //总页数
		int limit = 8;
		totalCount = productDao.findCountByCsid(csid);
		if (totalCount%limit==0) {
			totalPage = totalCount/limit;
		} else {
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalCount(totalCount);
		pageBean.setLimit(limit);
		pageBean.setTotalPage(totalPage);
		int begin = (page-1)*limit; 	//开始查询位置
		List<Product> list = productDao.findProductByCsid(csid, begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	
}
