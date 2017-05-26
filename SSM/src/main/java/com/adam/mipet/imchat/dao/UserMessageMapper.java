package com.adam.mipet.imchat.dao;

import com.adam.mipet.imchat.entity.UserMessage;
import com.adam.mipet.imchat.entity.UserMessageExample;
import java.util.List;

public interface UserMessageMapper {
	
	int countByExample(UserMessageExample example);
	
	int deleteByExample(UserMessageExample example);
	
	int deleteByPrimaryKey(Integer id);
	
	int insert(UserMessage record);
	
	List<UserMessage> selectByExample(UserMessageExample example);
	
	UserMessage selectByPrimaryKey(Integer id);
}