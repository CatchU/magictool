package com.magic.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 备注：返回数据 map
 * @author liaoyy@belink.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	public R() {
		put("code", 0);
		put("msg", "操作成功！");
	}
	
	public static R error() {
		return error(500, "系统繁忙，请联系管理员！");
	}
	
	public static R error(String msg) {
		return error(500, msg);
	}
	
	public static R error(int code, String defaultMsg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", ErPropertyUtils.getStrValue(code, defaultMsg));
		return r;
	}
	
	public static R error(int code) {
		R r = new R();
		String msg = ErPropertyUtils.getStrValue(code, "系统未知错误！");
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}