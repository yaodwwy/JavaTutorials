package com.adam.mipet.comments.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.comments.dao.UserCommentMapper;
import com.adam.mipet.comments.entity.UserComment;
import com.adam.mipet.comments.entity.UserCommentExample;
import com.adam.mipet.comments.service.IUserCommentService;

@Service
public class UserCommentServiceImpl implements IUserCommentService {
	
	@Resource
	UserCommentMapper userCommentMapper;
	
	@Override
	public int countByExample(UserCommentExample example) {
	
		return userCommentMapper.countByExample(example);
	}
	
	@Override
	public int deleteByExample(UserCommentExample example) {
	
		return userCommentMapper.deleteByExample(example);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		return userCommentMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(UserComment record) {
	
		return userCommentMapper.insert(record);
	}
	
	@Override
	public int insertSelective(UserComment record) {
	
		return userCommentMapper.insertSelective(record);
	}
	
	@Override
	public List<UserComment> selectByExample(UserCommentExample example) {
	
		return userCommentMapper.selectByExample(example);
	}
	
	@Override
	public UserComment selectByPrimaryKey(Integer id) {
	
		return userCommentMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByExampleSelective(UserComment record, UserCommentExample example) {
	
		return userCommentMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int updateByExample(UserComment record, UserCommentExample example) {
	
		return userCommentMapper.updateByExample(record, example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(UserComment record) {
	
		return userCommentMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int updateByPrimaryKey(UserComment record) {
	
		return userCommentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserComment> queryUserCommentListById(UserCommentExample example) {
	
		return userCommentMapper.queryUserCommentListById(example);
	}
}
