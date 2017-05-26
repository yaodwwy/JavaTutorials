package com.adam.sys.userfunc.service;

import java.util.List;
import java.util.Map;

import com.adam.sys.userfunc.entity.RoleMenu;

public interface IRoleMenuService {
	
	int insert(List<RoleMenu> list);
	
	int delete(Map<String, Object> map);
}
