package com.lovo.action;


import com.alibaba.fastjson.JSON;

public class ResultContent {
	private String url;
	private Object obj;
	
	public ResultContent(String url) {
		this.url = url;
	}
	
	public ResultContent(Object obj) {
		this.obj = obj;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getJson() {
		return JSON.toJSONString(obj);
	}
}
