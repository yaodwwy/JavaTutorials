package com.adam.sys.apilist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.constant.Constant;
import com.adam.sys.apilist.entity.ApiEntity;
import com.adam.sys.apilist.service.IApiEntityService;
import com.adam.sys.userfunc.entity.Menu;
import com.adam.sys.userfunc.entity.User;
import com.adam.sys.userfunc.service.IMenuService;

/**
 * 
 * 类名称：ApiEntityController
 * 类描述：api文档发布控制器
 * 创建人：yaoyanbing
 * 时间： 2016年1月19日 上午10:53:50 
 * 备注：
 */
@Controller
@RequestMapping("/document")
public class ApiInfoController {
	@Resource
	private IApiEntityService apiEntityService;
	@Resource
	private IMenuService menuService;

	@RequestMapping("menu")
	public String queryByTittle(ApiEntity apiinfo,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		Integer id = 5;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {}
		String title = menuService.queryNameByid(id);
		if(null!=title){
			map.put("title", title);
		}
		List<ApiEntity> query = apiEntityService.query(map);
		request.setAttribute("apiList", query);
		request.setAttribute("title",title);
		return "sys/api_manage";
	}
	/**
	 * Web页面内容自动展示
	 * @param apiinfo
	 * @param requst
	 * @return
	 */
	@RequestMapping("list")
	public String queryWebByTittle(ApiEntity apiinfo,HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		Integer id = 5;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {}
		String title = menuService.queryNameByid(id);
		
		if(null!=title){
			map.put("title", title);
		}
		List<ApiEntity> query = apiEntityService.query(map);
		map.put("status", 0);
		map.put("parentId", 0);
		List<Menu> queryMainMenu = menuService.queryMainMenu(map);
		//API菜单ID
		List<Menu> menuList = menuService.queryMenuByParentId(2);
		request.setAttribute("menuList", menuList);
		request.setAttribute("mainMenuList", queryMainMenu);
		request.setAttribute("apiList", query);
		request.setAttribute("title",title);
		return "../apidoc/api";
	}
	
	@RequestMapping("query")
	@ResponseBody
	public List<ApiEntity> query(ApiEntity apiinfo,HttpServletRequest requst) {
		Map<String,Object> map = new HashMap<String, Object>();
		if(null!=apiinfo && null!=apiinfo.getTitle()){
			map.put("title", apiinfo.getTitle());
		}
		return apiEntityService.query(map);
	}
	@RequestMapping("insert")
	@ResponseBody
	public Map<String,Object> insert(ApiEntity apiinfo,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			apiinfo.setCreator(user.getUsername());
			apiinfo.setUpdator(user.getUsername());
			apiEntityService.insert(apiinfo);
			return Constant.MSG.SAVE_OK();
		} catch (Exception e) {
			return Constant.MSG.SAVE_ERROR();
		}
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpSession session, ApiEntity apiinfo) {
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			apiinfo.setUpdator(user.getUsername());
			apiinfo.setIsDeleted(1);
			apiEntityService.delete(apiinfo);
			return Constant.MSG.DEL_OK();
		} catch (Exception e) {
			return Constant.MSG.DEL_ERROR();
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(ApiEntity apiinfo,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
				apiinfo.setUpdator(user.getUsername());
				apiEntityService.update(apiinfo);
				return Constant.MSG.UPD_OK();
		} catch (Exception e) {
			return Constant.MSG.UPD_ERROR();
		}
	}
}
