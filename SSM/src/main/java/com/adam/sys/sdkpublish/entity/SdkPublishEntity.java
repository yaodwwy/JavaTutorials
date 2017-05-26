package com.adam.sys.sdkpublish.entity;

import com.adam.common.entity.BaseEntity;

/**
 * 类名称：SdkPublishEntity
 * 类描述：sdk版本控制实体
 *
 */
public class SdkPublishEntity extends BaseEntity {
	
	private static final long serialVersionUID = 8011680736247214428L;
	//ID 
	private Integer id;
	//版本
	private String version;
	//SDK下载路径
	private String url;
	//应用名
	private String sdkName;
	//版本内容
	private String content;
	//SDK使用说明
	private String sdkinfo;
	//SDK调用示例
	private String sdkinfoEx;
	//是否强制更新
	private Integer isForce;
	//是否历史版本
	private Integer isHistory;
	
	
	
	public String getSdkinfoEx() {
		return sdkinfoEx;
	}
	public void setSdkinfoEx(String sdkinfoEx) {
		this.sdkinfoEx = sdkinfoEx;
	}
	public String getSdkinfo() {
		return sdkinfo;
	}
	public void setSdkinfo(String sdkinfo) {
		this.sdkinfo = sdkinfo;
	}
	public Integer getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(Integer isHistory) {
		this.isHistory = isHistory;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSdkName() {
		return sdkName;
	}
	public void setSdkName(String sdkName) {
		this.sdkName = sdkName;
	}

	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getIsForce() {
		return isForce;
	}
	public void setIsForce(Integer isForce) {
		this.isForce = isForce;
	}
	
	
}
