package com.adam.mipet.imchat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.imchat.dao.UserMessageMapper;
import com.adam.mipet.imchat.entity.UserMessage;
import com.adam.mipet.imchat.entity.UserMessageExample;
import com.adam.mipet.imchat.service.IUserMessageService;

@Service
public class UserMessageServiceImpl implements IUserMessageService {
	
	@Resource
	UserMessageMapper userMessageMapper;
	
	@Override
	public int countByExample(UserMessageExample example) {
	
		// TODO Auto-generated method stub
		return userMessageMapper.countByExample(example);
	}
	
	@Override
	public int deleteByExample(UserMessageExample example) {
	
		// TODO Auto-generated method stub
		return userMessageMapper.deleteByExample(example);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return userMessageMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(UserMessage record) {
		// TODO Auto-generated method stub
		return userMessageMapper.insert(record);
	}
	
	@Override
	public List<UserMessage> selectByExample(UserMessageExample example) {
	
		// TODO Auto-generated method stub
		return userMessageMapper.selectByExample(example);
	}
	
	@Override
	public UserMessage selectByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return userMessageMapper.selectByPrimaryKey(id);
	}
}
