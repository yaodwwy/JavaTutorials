package com.adam.sys.userfunc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.userfunc.dao.RoleDao;
import com.adam.sys.userfunc.entity.Role;
import com.adam.sys.userfunc.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Resource
	private RoleDao roleMapper;
	
	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return roleMapper.queryAll();
	}

	@Override
	public int delete(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.delete(record);
	}

	@Override
	public int insert(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.insert(record);
	}

	@Override
	public int update(Role record) {
		// TODO Auto-generated method stub
		return roleMapper.update(record);
	}

}
