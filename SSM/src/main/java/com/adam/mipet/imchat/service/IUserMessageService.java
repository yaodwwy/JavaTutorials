package com.adam.mipet.imchat.service;

import java.util.List;

import com.adam.mipet.imchat.entity.UserMessage;
import com.adam.mipet.imchat.entity.UserMessageExample;

public interface IUserMessageService {
	
	int countByExample(UserMessageExample example);
	
	int deleteByExample(UserMessageExample example);
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(UserMessage record);
	
	List<UserMessage> selectByExample(UserMessageExample example);
	
	UserMessage selectByPrimaryKey(Integer id);
}
