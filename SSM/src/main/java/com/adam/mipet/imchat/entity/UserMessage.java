package com.adam.mipet.imchat.entity;

import java.util.Date;

public class UserMessage {
	
	private Integer id;// id
	
	private Integer senderId;// 发送人
	
	private Integer receiverId;// 接收人
	
	private String message;// 消息
	
	private Date sendTime;// 发送时间
	
	public Integer getId() {
	
		return id;
	}
	
	public void setId(Integer id) {
	
		this.id = id;
	}
	
	public Integer getSenderId() {
	
		return senderId;
	}
	
	public void setSenderId(Integer senderId) {
	
		this.senderId = senderId;
	}
	
	public Integer getReceiverId() {
	
		return receiverId;
	}
	
	public void setReceiverId(Integer receiverId) {
	
		this.receiverId = receiverId;
	}
	
	public String getMessage() {
	
		return message;
	}
	
	public void setMessage(String message) {
	
		this.message = message;
	}
	
	public Date getSendTime() {
	
		return sendTime;
	}
	
	public void setSendTime(Date sendTime) {
	
		this.sendTime = sendTime;
	}
}