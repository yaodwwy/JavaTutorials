package com.adam.sys.apilist.entity;

import com.adam.common.entity.BaseEntity;

public class ApiEntity extends BaseEntity{
	private Integer id;//编号
	private String title;//标题
	private String content;//内容
	private String contentBackup;//最近一次的历史记录
	private ApiInfoType apiInfoType;//信息类型
	private Integer sort;//排序
	
	
	public String getContentBackup() {
		return contentBackup;
	}
	public void setContentBackup(String contentBackup) {
		this.contentBackup = contentBackup;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ApiInfoType getApiInfoType() {
		return apiInfoType;
	}
	public void setApiInfoType(ApiInfoType apiInfoType) {
		this.apiInfoType = apiInfoType;
	}
	
	
}
