package com.adam.mipet.like.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.mipet.article.entity.UserArticle;
import com.adam.mipet.article.service.IArticleService;
import com.adam.mipet.like.entity.ArticleLike;
import com.adam.mipet.like.entity.ArticleLikeExample;
import com.adam.mipet.like.service.IArticleLike;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("like")
public class ArticleLikeController {
	
	@Resource
	IArticleLike articleLike;
	@Resource
	IArticleService articleService;
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String,Object> insert(Integer userId,Integer articleId){
		if (null == userId && null == articleId) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			ArticleLike Like = new ArticleLike();
			Like.setUserId(userId);
			Like.setArticleId(articleId);
			int isOk = articleLike.insertSelective(Like);
			if(isOk>0){
				UserArticle userArticle = articleService.selectByPrimaryKey(articleId);
				Integer likes = userArticle.getLikes()==null?1:userArticle.getLikes()+1;
				userArticle.setLikes(likes);
				articleService.updateByPrimaryKey(userArticle);
				return Constant.MSG.RESULT_SUCCESS("+1");
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.MSG.SYSERROR();
		}
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String,Object> delete(Integer userId,Integer articleId){
			if (null == userId && null == articleId) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			try {
			ArticleLikeExample articleLikeExample = new ArticleLikeExample();
			ArticleLikeExample.Criteria createCriteria = articleLikeExample.createCriteria();
			createCriteria.andArticleIdEqualTo(articleId);
			createCriteria.andUserIdEqualTo(userId);

			List<ArticleLike> selectByExample = articleLike.selectByExample(articleLikeExample);
			int isOk = 0;
			for (ArticleLike like : selectByExample) {
				isOk = articleLike.deleteByPrimaryKey(like.getId());
			}
			if(isOk>0){
				
				UserArticle userArticle = articleService.selectByPrimaryKey(articleId);
				Integer likes = userArticle.getLikes()==null?1:userArticle.getLikes()-1;
				userArticle.setLikes(likes);
				articleService.updateByPrimaryKey(userArticle);
				return Constant.MSG.RESULT_SUCCESS("-1");
				
			}
			return Constant.MSG.RESULT_ERROR();
			
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
}
