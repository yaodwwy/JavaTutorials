package com.adam.mipet.favorites.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.mipet.article.service.IArticleService;
import com.adam.mipet.comments.service.IUserCommentService;
import com.adam.mipet.favorites.entity.UserFavs;
import com.adam.mipet.favorites.entity.UserFavsExample;
import com.adam.mipet.favorites.entity.UserFavsExample.Criteria;
import com.adam.mipet.favorites.service.IUserFavsService;
import com.adam.mipet.user.service.IMipetUserService;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("/favs")
public class FavsController {
	
	@Resource
	IUserFavsService userFavsService;
	
	@Resource
	IArticleService articleService;
	
	@Resource
	IMipetUserService mipetUserService;
	
	@Resource
	IUserCommentService userCommentService;
	
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String,Object> insert(Integer userId,Integer articleId){
		try {
			UserFavs userFavs = new UserFavs();
			userFavs.setUserId(userId);
			userFavs.setArticleId(articleId);
			int isOk = userFavsService.insertSelective(userFavs);
			if(isOk>0){
				return Constant.MSG.RESULT_SUCCESS("加入收藏成功");
			}
			return Constant.MSG.RESULT_ERROR("加入收藏失败");
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	/**
	 * 移除收藏
	 * @param userId
	 * @param articleId
	 * @return
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String,Object> delete(Integer userId,Integer articleId){
		try {
			if (null == userId && null == articleId) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			UserFavsExample userFavsExample = new UserFavsExample();
			
			Criteria criteria = userFavsExample.createCriteria();
			criteria.andArticleIdEqualTo(articleId);
			criteria.andUserIdEqualTo(userId);
			
			List<UserFavs> selectByExample = userFavsService.selectByExample(userFavsExample);
			int isOk = 0;
			for (UserFavs userFavs : selectByExample) {
				int artid=userFavs.getId();
				isOk = userFavsService.deleteByPrimaryKey(userFavs.getId());
			}
			if(isOk>0){
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 查询当前是否已收藏
	 * @param userId
	 * @param articleId
	 * @return
	 */
	
	@RequestMapping("query.do")
	@ResponseBody
	public Map<String,Object> query(Integer userId,Integer articleId){
		try {
			if (null == userId && null == articleId) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			UserFavsExample userFavsExample = new UserFavsExample();
			
			Criteria criteria = userFavsExample.createCriteria();
			criteria.andArticleIdEqualTo(articleId);
			criteria.andUserIdEqualTo(userId);
			
			List<UserFavs> selectByExample = userFavsService.selectByExample(userFavsExample);
			if(selectByExample!=null && selectByExample.size()>0){
				return Constant.MSG.RESULT_SUCCESS("0");//0表示已收藏，-1表示未收藏
			}
			return Constant.MSG.RESULT_ERROR("-1");
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 查询全部收藏列表
	 * @param userId
	 * @param page
	 * @param rows
	 * @param searchinput
	 * @return
	 */
	@RequestMapping("queryall.do")
	@ResponseBody
	public Map<String,Object> queryall(Integer userId,Integer page, Integer rows,String searchKey){
		try {
			if (null == userId || userId <= 0) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			UserFavsExample userFavsExample = new UserFavsExample();
			userFavsExample.setUserId(userId);
			userFavsExample.setStart(rows * (page - 1));
			userFavsExample.setLimit(rows);
			if(searchKey!=null && !"".equals(searchKey)){
				userFavsExample.setSearchinput(searchKey);
			}
			List<UserFavs> favsList = userFavsService.selectMyCollections(userFavsExample);
			if(favsList==null || favsList.size()<=0){
				return Constant.MSG.RESULT_ERROR("没有查到相关数据");
			}
			return Constant.MSG.RESULT_SUCCESS(favsList);
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
}





