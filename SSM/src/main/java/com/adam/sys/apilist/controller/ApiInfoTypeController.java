package com.adam.sys.apilist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.constant.Constant;
import com.adam.sys.apilist.entity.ApiInfoType;
import com.adam.sys.apilist.service.IApiInfoTypeService;
import com.adam.sys.userfunc.entity.User;

/**
 * 
 * 类名称：ApiInfoTypeController
 * 类描述：api文档发布类型控制器
 * 创建人：yaoyanbing
 * 时间： 2016年1月19日 上午10:53:50 
 * 备注：
 */
@Controller
@RequestMapping("/apitype")
public class ApiInfoTypeController {
	@Resource
	private IApiInfoTypeService apiInfoTypeService;

	@RequestMapping("query")
	@ResponseBody
	public List<ApiInfoType> query(String title) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("title", title);
		List<ApiInfoType> query = apiInfoTypeService.query(map);
		return query;
	}
	@RequestMapping("queryWithOutBeing")
	@ResponseBody
	public List<ApiInfoType> queryWithOutBeing(String title) {
		return apiInfoTypeService.queryWithOutBeing(title);
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public Map<String,Object> insert(ApiInfoType apiInfoType,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			if(apiInfoType.getUrl().indexOf("#")!=0){
				apiInfoType.setUrl("#"+apiInfoType.getUrl());
			}
			apiInfoType.setCreator(user.getUsername());
			apiInfoType.setUpdator(user.getUsername());
			apiInfoTypeService.insert(apiInfoType);
			return Constant.MSG.SAVE_OK();
		} catch (Exception e) {
			return Constant.MSG.SAVE_ERROR();
		}
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpSession session, ApiInfoType apiInfoType) {
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			apiInfoType.setUpdator(user.getUsername());
			apiInfoTypeService.delete(apiInfoType);
			return Constant.MSG.DEL_OK();
		} catch (Exception e) {
			return Constant.MSG.DEL_ERROR();
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(ApiInfoType apiInfoType,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			apiInfoType.setUpdator(user.getUsername());
			apiInfoTypeService.update(apiInfoType);
			return Constant.MSG.UPD_OK();
		} catch (Exception e) {
			return Constant.MSG.UPD_ERROR();
		}
	}
	
	
	
	@RequestMapping("updateTypeSortDown")
	@ResponseBody
	public Map<String,Object> updateTypeSortDown(String name){
		try {
			return apiInfoTypeService.updateNameSortDown(name)>0?Constant.MSG.MAP_OK():Constant.MSG.MAP_ERR();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	@RequestMapping("updateTypeSortUp")
	@ResponseBody
	public Map<String,Object> updateMenuSortUp(String name){
		try {
			return apiInfoTypeService.updateNameSortUp(name)>0?Constant.MSG.MAP_OK():Constant.MSG.MAP_ERR();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	
}
