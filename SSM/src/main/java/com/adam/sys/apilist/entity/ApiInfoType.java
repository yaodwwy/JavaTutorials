package com.adam.sys.apilist.entity;

import com.adam.common.entity.BaseEntity;

public class ApiInfoType extends BaseEntity{
	private String name;//名字/主键
	private String url;//锚点链接/主键
	private Integer sort;//排序
	
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
