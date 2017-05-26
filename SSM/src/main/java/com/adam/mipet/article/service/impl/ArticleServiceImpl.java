package com.adam.mipet.article.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.article.dao.UserArticleMapper;
import com.adam.mipet.article.entity.UserArticle;
import com.adam.mipet.article.entity.UserArticleExample;
import com.adam.mipet.article.service.IArticleService;
@Service
public class ArticleServiceImpl implements IArticleService {

	@Resource
	UserArticleMapper userArticleMapper;

	@Override
	public int countByExample(UserArticleExample example) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(UserArticleExample example) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserArticle record) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.insert(record);
	}

	@Override
	public int insertSelective(UserArticle record) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.insertSelective(record);
	}

	@Override
	public List<UserArticle> selectByExample(UserArticleExample example) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.selectByExample(example);
	}

	@Override
	public UserArticle selectByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(UserArticle record, UserArticleExample example) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(UserArticle record, UserArticleExample example) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(UserArticle record) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserArticle record) {
	
		// TODO Auto-generated method stub
		return userArticleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserArticle> queryArticleListById(UserArticleExample example) {
	
		return userArticleMapper.queryArticleListById(example);
	}

	@Override
	public UserArticle selectArticle_By_ArticleId(UserArticleExample userArticleExample) {
	
		return userArticleMapper.selectArticle_By_ArticleId(userArticleExample);
	}

	
	
}
