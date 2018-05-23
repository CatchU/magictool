package com.magic.util;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * 备注：返回 JSONString
 * 
 * @author liaoyy@belink.com
 */
public class RS {
	/**
	 * 备注：ok
	 * 
	 * @author liaoyy@belink.com
	 * @param msg
	 *            string
	 * @return
	 */
	public static String ok() {
		return JSON.toJSONString(R.ok());
	}

	public static String ok(String msg) {
		return JSON.toJSONString(R.ok(msg));
	}

	/**
	 * 备注：
	 * 
	 * @author liaoyy@belink.com
	 * @param obj
	 *            map
	 * @return
	 */
	public static String ok(Map<String, Object> obj) {
		return JSON.toJSONString(R.ok(obj));
	}
	
 
	public static String okPut(String key,Object value) {
		return JSON.toJSONString(R.ok().put(key, value));
	}

	/**
	 * 备注：
	 * 
	 * @author liaoyy@belink.com
	 * @return
	 */
	public static String error() {
		return JSON.toJSONString(R.error());
	}

	/**
	 * 备注：
	 * 
	 * @author liaoyy@belink.com
	 * @param code
	 *            错误代码
	 * @return
	 */
	public static String error(int code) {
		return JSON.toJSONString(R.error(code));
	}

	/**
	 * 备注：
	 * 
	 * @author liaoyy@belink.com
	 * @param msg
	 * @return
	 */
	public static String error(String msg) {
		return JSON.toJSONString(R.error(msg));
	}

	/**
	 * 备注：
	 * 
	 * @author liaoyy@belink.com
	 * @param code
	 * @param defaultMsg
	 *            默认错误信息
	 * @return
	 */
	public static String error(int code, String defaultMsg) {
		return JSON.toJSONString(R.error(code, defaultMsg));
	}
}
