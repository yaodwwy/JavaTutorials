package com.adam.sys.userfunc.entity;

import com.adam.common.entity.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = 6124866587726068381L;

	private Integer id;// id

	private String username;// 用户名

	private String pwd;// 密码

	private Integer status;// 状态

	private String fullname;// 真实姓名

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * username
	 *
	 * @return the username
	 * @since 1.0.0
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname == null ? null : fullname.trim();
	}

}