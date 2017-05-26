package com.adam.mipet.article.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.entity.Paging;
import com.adam.common.util.MybatisUtil;
import com.adam.mipet.article.dao.ArticleImageMapper;
import com.adam.mipet.article.entity.ArticleImage;
import com.adam.mipet.article.entity.ArticleImageExample;
import com.adam.mipet.article.entity.UserArticle;
import com.adam.mipet.article.entity.UserArticleExample;
import com.adam.mipet.article.entity.UserArticleExample.Criteria;
import com.adam.mipet.article.service.IArticleService;
import com.adam.mipet.article.service.IImageService;
import com.adam.mipet.comments.service.IUserCommentService;
import com.adam.mipet.favorites.service.IUserFavsService;
import com.adam.mipet.like.service.IArticleLike;
import com.adam.mipet.user.service.IMipetUserService;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Resource
	IArticleService articleService;

	@Resource
	IMipetUserService mipetUserService;

	@Resource
	IUserCommentService userCommentService;

	@Resource
	IUserFavsService userFavService;

	@Resource
	IArticleLike articleLike;
	@Resource
	IImageService iImageService;
	@Resource
	ArticleImageMapper articleImageMapper;

	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String, Object> insert(UserArticle userArticle, String imageUrl) {

		if (null == userArticle || null == userArticle.getTitle()
				|| null == userArticle.getHtmlContent()
				|| null == userArticle.getUserId()
				|| null == userArticle.getHtmlContent()) {
			return Constant.MSG.MAP_ILLEGAL();
		}

		try {
			articleService.insert(userArticle);
			if (imageUrl != null && imageUrl != "") {
				String[] imgs = imageUrl.split(",");
				for (int i = 0; i < imgs.length; i++) {
					ArticleImage articleImage = new ArticleImage();
					articleImage.setImageUrl(imgs[i]);
					articleImage.setArticleId(userArticle.getId());
					iImageService.insert(articleImage);
				}
			}
			return Constant.MSG.RESULT_SUCCESS(userArticle.getId());
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String, Object> delete(Integer id, Integer userId) {

		try {
			if (null == id || null == userId) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			UserArticle userArticle = articleService.selectByPrimaryKey(id);
			userArticle.setDeleted(1);
			int isOk = articleService.updateByPrimaryKey(userArticle);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS(userArticle.getId());
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(UserArticle userArticle, String imageUrl,
			Integer sysId) {
		try {
			if (null == userArticle || null == userArticle.getTitle()
					|| null == userArticle.getHtmlContent()
					|| null == userArticle.getUserId() || null == sysId) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			ArticleImageExample articleImageExample = new ArticleImageExample();
			articleImageExample.createCriteria().andArticleIdEqualTo(
					userArticle.getId());
			List<ArticleImage> articleImages = iImageService
					.selectByExample(articleImageExample);
			if (articleImages != null) {
				for (int i = 0; i < articleImages.size(); i++) {
					int id = articleImages.get(i).getId();
					iImageService.deleteByPrimaryKey(id);
				}
			}
			int isOk = 0;
			if (imageUrl != null) {
				String[] imgs = imageUrl.split(",");
				for (int i = 0; i < imgs.length; i++) {
					ArticleImage articleImage = new ArticleImage();
					articleImage.setImageUrl(imgs[i]);
					articleImage.setArticleId(userArticle.getId());
					iImageService.insert(articleImage);
				}
			}
             userArticle.setUpdateTime(new Date());
			isOk = articleService.updateByPrimaryKeySelective(userArticle);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS(userArticle.getId());
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	@RequestMapping("updateshow.do")
	@ResponseBody
	public Map<String, Object> update(Integer id, Integer status) {
		try {
			if (null == id || null == status) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			int isOk = 0;
			UserArticle userArticle = new UserArticle();
			userArticle.setId(id);
			userArticle.setStatus(status);
			isOk = articleService.updateByPrimaryKeySelective(userArticle);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	/**
	 * 查询单个文章SQL优化版本
	 * @param articleId
	 * @return
	 */
	@RequestMapping("queryone.do")
	@ResponseBody
	public Map<String, Object> queryone(UserArticleExample userArticleExample) {
		if (userArticleExample == null || userArticleExample.getId() ==null) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		UserArticle selectArticle_By_ArticleId = articleService.selectArticle_By_ArticleId(userArticleExample);
		return Constant.MSG.RESULT_SUCCESS(selectArticle_By_ArticleId);
	}

	/**
	 * 根据版块和用户id查询 搜索我的收藏/我的故事/所有板块
	 * 
	 * @param userId
	 * @param portion
	 * @param page
	 * @param rows
	 * @param searchKey
	 * @return
	 */
	@RequestMapping("querybyid.do")
	@ResponseBody
	public Map<String, Object> queryByUserId(Integer userId, Integer portion,
			Integer page, Integer rows, String searchKey) {
		List<UserArticle> queryArticleListById = null;
		if (page == null || rows == null) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		UserArticleExample userArticleExample = new UserArticleExample();
		userArticleExample.setStart(rows * (page - 1));
		userArticleExample.setLimit(rows);
		try {
			if (portion != null && portion != -1) {
				userArticleExample.setPortion(portion);
			} else {
				userArticleExample.setId(userId);
			}
			if (searchKey != null && !"".equals(searchKey)) {
				userArticleExample.setSearchinput(searchKey);
			}
			queryArticleListById = articleService
					.queryArticleListById(userArticleExample);
			if (queryArticleListById != null) {
				return Constant.MSG.RESULT_SUCCESS(queryArticleListById);
			} else {
				return Constant.MSG.RESULT_ERROR();
			}
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	@RequestMapping("querylist.do")
	@ResponseBody
	public Paging<UserArticle> queryList(String title, String portion, Integer page,
			Integer rows,String sort,String order) {
		Paging<UserArticle> pageList = new Paging<UserArticle>();
		if (page == null || rows == null) {
			return pageList;
		}
		UserArticleExample userArticleExample = new UserArticleExample();
		Criteria criteria = userArticleExample.createCriteria();
		criteria.andDeletedEqualTo(0);
		
		if (null != title && !"".equals(title)){
			criteria.andTitleLike("%" + title + "%");
		}
		 if(null != portion && !"".equals(portion)){
			criteria.andPortionEqualTo(Integer.parseInt(portion));
		}
		int start = rows * (page - 1);
            if(sort != null && order != null){
			userArticleExample.setOrder(order);
			sort = MybatisUtil.getSQLName(sort);
			userArticleExample.setSort(sort);
		}
		userArticleExample.setStart(start);
		userArticleExample.setLimit(rows);
		List<UserArticle> selectByExampleWithBLOBs = articleService.selectByExample(userArticleExample);
		pageList.setRows(selectByExampleWithBLOBs);
		pageList.setTotal(articleService.countByExample(userArticleExample));
		return pageList;

	}
}
