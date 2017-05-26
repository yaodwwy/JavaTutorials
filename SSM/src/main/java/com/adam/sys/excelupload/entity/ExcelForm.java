package com.adam.sys.excelupload.entity;

import com.adam.common.entity.BaseEntity;

public class ExcelForm extends BaseEntity {
private Integer id;//id
private String name;//名字
private String url;//文件位置
private String email;//接收Excel邮箱

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
