package com.adam.mipet.article.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.article.dao.ArticleImageMapper;
import com.adam.mipet.article.entity.ArticleImage;
import com.adam.mipet.article.entity.ArticleImageExample;
import com.adam.mipet.article.service.IImageService;

@Service
public class ImageServiceImpl implements IImageService {
	
	@Resource
	ArticleImageMapper articleImageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return articleImageMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(ArticleImage record) {
		return articleImageMapper.insert(record);
	}
	
	@Override
	public List<ArticleImage> selectByExample(ArticleImageExample example) {
		return articleImageMapper.selectByExample(example);
	}
}
