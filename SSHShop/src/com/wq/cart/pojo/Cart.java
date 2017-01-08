/**
 * 
 */
package com.wq.cart.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author youto8023
 * 购物车的对象
 */
public class Cart implements Serializable {
	//购物项集合,商品id为Key,hashmap是无序的
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//商品总计
	private double total;
	
	//Map不利于遍历，单列比较好
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//1.添加购物项
	public void addCartItem(CartItem cartItem){
		Integer pid = cartItem.getProduct().getPid();
		//判断购物车商品是否已经存在
		if (!map.containsKey(pid)) {
			 map.put(pid, cartItem);
		} else {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(cartItem.getCount()+_cartItem.getCount());
		}
		//算商品总计
		total+=cartItem.getSubCount();
	}
	
	//2.删除购物项
	public void removeCartItem(Integer pid){
		CartItem cartItem = map.remove(pid);
		total-=cartItem.getSubCount();
	}
	
	//3.清空购物车
	public void clearCart(){
		map.clear();
	}
	public double getTotal() {
		return total;
	}
	
}
