package com.adam.sys.userfunc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.sys.userfunc.entity.UserRole;
import com.adam.sys.userfunc.service.IUserRoleService;


@Controller
@RequestMapping("/userrole")
@SuppressWarnings("all")
public class UserRoleController {

	@Resource
	private IUserRoleService userRoleService;
	
	@ResponseBody
	@RequestMapping("query")
	public List<UserRole> query(String userId){
		return userRoleService.query(userId);
	}
	
}
