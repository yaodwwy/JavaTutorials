package com.adam.sys.userfunc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.constant.Constant;
import com.adam.sys.userfunc.entity.RoleMenu;
import com.adam.sys.userfunc.entity.User;
import com.adam.sys.userfunc.service.IRoleMenuService;


@Controller
@RequestMapping("/rolemenu")
@SuppressWarnings("all")
public class RoleMenuController {

	@Resource
	private IRoleMenuService roleMenuService;
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(Integer roleId,String menuId){
		try {
			String[] menuIds = menuId.split(",");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("list",menuIds);
			map.put("roleId", roleId);
			roleMenuService.delete(map);
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public Map<String,Object> insert(HttpSession session,Integer roleId,String menuId){
		try {
			User user = (User)session.getAttribute("admin");
			String[] menuIds = menuId.split(",");
			List<RoleMenu> list = new ArrayList<RoleMenu>();
			RoleMenu roleMenu;
			for(String menuid:menuIds){
				roleMenu = new RoleMenu();
				roleMenu.setMenuId(Integer.parseInt(menuid));
				roleMenu.setRoleId(roleId);
				roleMenu.setCreator(user.getUsername());
				list.add(roleMenu);
			}
			roleMenuService.insert(list);
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			System.out.println(e.toString());
			return Constant.MSG.MAP_ERR();
		}
	}
	
}
