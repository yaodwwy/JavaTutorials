package com.adam.sys.userfunc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.adam.common.constant.Constant;
import com.adam.sys.userfunc.entity.Role;
import com.adam.sys.userfunc.entity.User;
import com.adam.sys.userfunc.service.IRoleService;

@Controller
@RequestMapping("/role")
@SuppressWarnings("all")
public class RoleController {

	@Resource
	private IRoleService roleService;
	
	@ResponseBody
	@RequestMapping("queryAll")
	public List<Role> queryAll(){
		return roleService.queryAll();
	}
	
	@ResponseBody
	@RequestMapping("insert")
	public void insert(HttpServletResponse response,HttpSession session,Role role)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			role.setCreator(((User)session.getAttribute("admin")).getUsername());
			roleService.insert(role);
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_OK()));
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_ERR()));
		}
	}
	
	@ResponseBody
	@RequestMapping("update")
	public void update(HttpServletResponse response,HttpSession session,Role role)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			role.setUpdator(((User)session.getAttribute("admin")).getUsername());
			roleService.update(role);
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_OK()));
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_ERR()));
		}
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public Map<String,Object> delete(HttpSession session,Integer id)throws IOException{
		try {
			Role role = new Role();
			role.setUpdator(((User)session.getAttribute("admin")).getUsername());
			role.setId(id);
			roleService.delete(role);
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
}
