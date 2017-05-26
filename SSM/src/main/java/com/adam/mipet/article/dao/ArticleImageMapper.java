package com.adam.mipet.article.dao;

import com.adam.mipet.article.entity.ArticleImage;
import com.adam.mipet.article.entity.ArticleImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleImageMapper {
    int countByExample(ArticleImageExample example);

    int deleteByExample(ArticleImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleImage record);

    int insertSelective(ArticleImage record);

    List<ArticleImage> selectByExample(ArticleImageExample example);

    ArticleImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleImage record, @Param("example") ArticleImageExample example);

    int updateByExample(@Param("record") ArticleImage record, @Param("example") ArticleImageExample example);

    int updateByPrimaryKeySelective(ArticleImage record);

    int updateByPrimaryKey(ArticleImage record);
}