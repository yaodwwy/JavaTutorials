package com.adam.mipet.article.service;

import java.util.List;

import com.adam.mipet.article.entity.ArticleImage;
import com.adam.mipet.article.entity.ArticleImageExample;

public interface IImageService {
    int deleteByPrimaryKey(Integer id);
    int insert(ArticleImage record);
    List<ArticleImage> selectByExample(ArticleImageExample example);
}
