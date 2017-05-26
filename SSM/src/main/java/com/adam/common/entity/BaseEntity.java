package com.adam.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 类名称：BaseEntity
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8684637612708043903L;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建时间
	 */
	private Date createTime = new Date();
	/**
	 * 更新人
	 */
	private String updator;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否已删除
	 */
	private Integer isDeleted = 0;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * creator
	 *
	 * @return the creator
	 * @since 1.0.0
	 */

	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * updator
	 *
	 * @return the updator
	 * @since 1.0.0
	 */

	public String getUpdator() {
		return updator;
	}

	/**
	 * @param updator
	 *            the updator to set
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
