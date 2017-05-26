package com.adam.sys.userfunc.entity;

import com.adam.common.entity.BaseEntity;

public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 6987580732453499794L;

	private Integer id;// id

	private Integer roleId;// 权限id

	private String userId;// 用户id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}