package com.adam.sys.userfunc.dao;

import java.util.List;
import java.util.Map;

import com.adam.sys.userfunc.entity.RoleMenu;

public interface RoleMenuDao {

	int insert(List<RoleMenu> list);
	
	int delete(Map<String, Object> map);
}