package com.lovo.entity;

import com.lovo.util.CommonUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * °à¼¶
 * @author Âæê»
 *
 */
public class MyClass implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private Date createTime;
	
	public MyClass() {}
	
	public MyClass(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	public String getFormattedCreateTime() {
		return CommonUtil.formatDateToString(createTime);
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
