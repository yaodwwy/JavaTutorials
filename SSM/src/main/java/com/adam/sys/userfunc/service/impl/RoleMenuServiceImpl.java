package com.adam.sys.userfunc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.userfunc.dao.RoleMenuDao;
import com.adam.sys.userfunc.entity.RoleMenu;
import com.adam.sys.userfunc.service.IRoleMenuService;


@Service
public class RoleMenuServiceImpl implements IRoleMenuService {

	@Resource
	private RoleMenuDao roleMenuMapper;
	
	@Override
	public int insert(List<RoleMenu> list) {
		// TODO Auto-generated method stub
		return roleMenuMapper.insert(list);
	}

	@Override
	public int delete(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return roleMenuMapper.delete(map);
	}

}
