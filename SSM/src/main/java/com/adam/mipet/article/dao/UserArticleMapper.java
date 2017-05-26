package com.adam.mipet.article.dao;

import com.adam.mipet.article.entity.UserArticle;
import com.adam.mipet.article.entity.UserArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserArticleMapper {
    int countByExample(UserArticleExample example);

    int deleteByExample(UserArticleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserArticle record);

    int insertSelective(UserArticle record);

    List<UserArticle> selectByExample(UserArticleExample example);

    UserArticle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserArticle record, @Param("example") UserArticleExample example);

    int updateByExample(@Param("record") UserArticle record, @Param("example") UserArticleExample example);

    int updateByPrimaryKeySelective(UserArticle record);

    int updateByPrimaryKey(UserArticle record);
    
    List<UserArticle> queryArticleListById(UserArticleExample example);
    
    //SQL优化根据文章ID查详情
    UserArticle selectArticle_By_ArticleId(UserArticleExample userArticleExample);
    
}