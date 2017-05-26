package com.adam.mipet.comments.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.adam.common.entity.Paging;
import com.adam.mipet.article.entity.UserArticle;
import com.adam.mipet.article.service.IArticleService;
import com.adam.mipet.comments.entity.UserComment;
import com.adam.mipet.comments.entity.UserCommentExample;
import com.adam.mipet.comments.entity.UserCommentExample.Criteria;
import com.adam.mipet.comments.service.IUserCommentService;
import com.adam.mipet.user.entity.MipetUser;
import com.adam.mipet.user.service.IMipetUserService;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Resource
	IUserCommentService commentService;
	
	@Resource
	IArticleService articleService;

	@Resource
	IMipetUserService mipetUserService;
	
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String, Object> insert(UserComment userComment){
	
		if (userComment == null || userComment.getArticleId() == null || userComment.getContent() == null || userComment.getUserId() == null) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			int isok = 0;
			isok = commentService.insert(userComment);
			if (isok > 0) {
				//当插入评论时,原评论数加1
				UserArticle userArticle = articleService.selectByPrimaryKey(userComment.getArticleId());
				Integer comm = userArticle.getComments()==null?1:userArticle.getComments()+1;
				userArticle.setComments(comm);
				articleService.updateByPrimaryKey(userArticle);
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.MSG.SYSERROR();
		}
	}
	
//	@RequestMapping("delete.do")
//	@ResponseBody
//	public Map<String, Object> delete(Integer id) {
//		if (id == null || id <= 0) {
//			return Constant.MSG.MAP_ILLEGAL();
//		}
//		try {
//			int isok = 0;
//			isok = commentService.deleteByPrimaryKey(id);
//			if (isok > 0) {
//				return Constant.MSG.RESULT_SUCCESS();
//			}
//			return Constant.MSG.RESULT_ERROR();
//		} catch (Exception e) {
//			return Constant.MSG.SYSERROR();
//		}
//	}
	
	/**
	 * 根据用户id查询“我的评论”
	 * @param id
	 * @return
	 */
	@RequestMapping("querymy.do")
	@ResponseBody
	public Map<String, Object> queryMy(Integer id,Integer page,Integer rows) {
		if (id == null || id <= 0) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			UserCommentExample commentExample = new UserCommentExample();
			//分页
			commentExample.setStart(rows * (page - 1));
			commentExample.setLimit(rows);
			Criteria createCriteria = commentExample.createCriteria();
			createCriteria.andDeletedEqualTo(0);
			createCriteria.andUserIdEqualTo(id);
			List<UserComment> result = commentService.selectByExample(commentExample);
			JSONArray jsonArray = new JSONArray();
			for (UserComment userComment : result) {
				Integer articleId = userComment.getArticleId();
				UserArticle userArticle = articleService.selectByPrimaryKey(articleId);
				JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userComment));
				jsonObject.put("title", userArticle.getTitle());
				jsonArray.add(jsonObject);
			}
			if(jsonArray.size()>0){
				return Constant.MSG.RESULT_SUCCESS(jsonArray);
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 根据文章id查询“文章评论”
	 * @param id
	 * @return
	 */
	@RequestMapping("queryall.do")
	@ResponseBody
	public Map<String, Object> queryArticle(Integer id ,Integer page,Integer rows) {
		if (id == null || id <= 0) {
			return Constant.MSG.MAP_ILLEGAL();
		}
	
		try {
			UserCommentExample commentExample = new UserCommentExample();

			//分页
			commentExample.setStart(rows * (page - 1));
			commentExample.setLimit(rows);
			commentExample.createCriteria().andArticleIdEqualTo(id);
			List<UserComment> result = commentService.selectByExample(commentExample);
			JSONArray jsonArray = new JSONArray();
			for (UserComment userComment : result) {
				Integer userId = userComment.getUserId();
				MipetUser mipetUser = mipetUserService.querybyid(userId);
				JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userComment));
				jsonObject.put("mipetUser", mipetUser);
				jsonArray.add(jsonObject);
			}
			if(jsonArray.size()>0){
				return Constant.MSG.RESULT_SUCCESS(jsonArray);
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 根据文章id查询“文章评论”列表,用于EasyUi列表展示
	 * @param id
	 * @return
	 */
	@RequestMapping("queryList.do")
	@ResponseBody
	public Paging<UserComment> queryList(Integer articleId,Integer page, Integer rows) {
		Paging<UserComment> pageList = new Paging<UserComment>();
		if(page == null || rows == null || articleId == null){
			return pageList;
		}
		UserCommentExample userCommentExample = new UserCommentExample();
		userCommentExample.setArticleId(articleId);
		//用户总数量的查询条件
		userCommentExample.createCriteria().andArticleIdEqualTo(articleId);
		int start = rows * (page - 1);
		userCommentExample.setStart(start);
		userCommentExample.setLimit(rows);
		List<UserComment> commentList = commentService.queryUserCommentListById(userCommentExample);
		if(commentList != null && commentList.size()>0){
			pageList.setRows(commentList);
			pageList.setTotal(commentService.countByExample(userCommentExample));
		}
		return pageList;
	}
	/**
	 * 根据文章id删除评论
	 * @param id
	 * @return
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String, Object> deleteComment(UserComment userComment ,Integer sysId) {
		if (userComment==null||userComment.getId() == null || userComment.getUserId() == null || sysId == null) {
			return Constant.MSG.MAP_ILLEGAL();
		}
	
		try {
			UserCommentExample commentExample = new UserCommentExample();
			commentExample.createCriteria().andArticleIdEqualTo(userComment.getId());
			UserComment selectUserComment = commentService.selectByPrimaryKey(userComment.getId());
				int Ok=commentService.deleteByPrimaryKey(userComment.getId());
			if(Ok>0){
				UserArticle userArticle = articleService.selectByPrimaryKey(selectUserComment.getArticleId());
				if(userArticle!=null && userArticle.getComments()!=null && userArticle.getComments() >0){
					Integer comments = userArticle.getComments()-1;
					userArticle.setComments(comments);
					articleService.updateByPrimaryKey(userArticle);
				}
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	
	
	
}
