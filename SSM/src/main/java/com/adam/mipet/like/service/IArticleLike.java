package com.adam.mipet.like.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adam.mipet.like.entity.ArticleLike;
import com.adam.mipet.like.entity.ArticleLikeExample;


public interface IArticleLike {
	 int countByExample(ArticleLikeExample example);

	    int deleteByExample(ArticleLikeExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(ArticleLike record);

	    int insertSelective(ArticleLike record);

	    List<ArticleLike> selectByExample(ArticleLikeExample example);

	    ArticleLike selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") ArticleLike record, @Param("example") ArticleLikeExample example);

	    int updateByExample(@Param("record") ArticleLike record, @Param("example") ArticleLikeExample example);

	    int updateByPrimaryKeySelective(ArticleLike record);

	    int updateByPrimaryKey(ArticleLike record);
}
