package com.adam.mipet.like.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.like.dao.ArticleLikeMapper;
import com.adam.mipet.like.entity.ArticleLike;
import com.adam.mipet.like.entity.ArticleLikeExample;
import com.adam.mipet.like.service.IArticleLike;

@Service
public class ArticleLikeImpl implements IArticleLike {
	
	@Resource
	ArticleLikeMapper articleLikeMapper;
	
	@Override
	public int countByExample(ArticleLikeExample example) {
		return articleLikeMapper.countByExample(example);
	}
	
	@Override
	public int deleteByExample(ArticleLikeExample example) {
		return articleLikeMapper.deleteByExample(example);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return articleLikeMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(ArticleLike record) {
		return articleLikeMapper.insert(record);
	}
	
	@Override
	public int insertSelective(ArticleLike record) {
		return articleLikeMapper.insertSelective(record);
	}
	
	@Override
	public List<ArticleLike> selectByExample(ArticleLikeExample example) {
		return articleLikeMapper.selectByExample(example);
	}
	
	@Override
	public ArticleLike selectByPrimaryKey(Integer id) {
		return articleLikeMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByExampleSelective(ArticleLike record, ArticleLikeExample example) {
		return articleLikeMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int updateByExample(ArticleLike record, ArticleLikeExample example) {
		return articleLikeMapper.updateByExample(record, example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(ArticleLike record) {
		return articleLikeMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public int updateByPrimaryKey(ArticleLike record) {
		return articleLikeMapper.updateByPrimaryKey(record);
	}
}
