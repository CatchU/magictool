package com.magic.page;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 当前页码
	private int curPage;
	// 每页条数
	private int pageSize;

	public Query(Map<String, Object> params){
		this.putAll(params);

		// 分页参数, 如果不传默认当前页第一页，每页十条
		this.curPage = params.get("curPage")==null?1:Integer.parseInt(params.get("curPage").toString());
		this.pageSize = params.get("pageSize")==null?10:Integer.parseInt(params.get("pageSize").toString());
		//this.curPage = Integer.parseInt(params.get("curPage").toString());
		//this.pageSize = Integer.parseInt(params.get("pageSize").toString());
		this.put("offset", (curPage - 1) * pageSize);
		this.put("page", curPage);
		this.put("limit", pageSize);

		// 防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
		if (null != params.get("sidx") && null != params.get("order")) {
			String sidx = params.get("sidx").toString();
			String order = params.get("order").toString();
			this.put("sidx", SQLFilter.sqlInject(sidx));
			this.put("order", SQLFilter.sqlInject(order));
		}
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
