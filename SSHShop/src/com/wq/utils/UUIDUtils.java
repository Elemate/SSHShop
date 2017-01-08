/**
 * 
 */
package com.wq.utils;

import java.util.UUID;

/**
 *<p>Title:</p>
 *<p>用来生成注册码:</p> 
 *
 * @author 王庆
 * @date 2016年12月7日 上午11:09:09
 */
public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
