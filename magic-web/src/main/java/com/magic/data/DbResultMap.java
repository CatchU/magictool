package com.magic.data;

/**
 * @Description 封装mybatis查询类
 * @Author 刘俊重
 */
public class DbResultMap extends SfMap {

	private static final long serialVersionUID = 4272613146467658519L;

	public Long getId() {
		return reqLong("id");
	}

	public Long optId() {
		return getLong("id");
	}
}
