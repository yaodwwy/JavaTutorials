package com.adam.sys.userfunc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.userfunc.dao.UserRoleDao;
import com.adam.sys.userfunc.entity.UserRole;
import com.adam.sys.userfunc.service.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	private UserRoleDao userRoleMapper;
	
	@Override
	public int insert(List<UserRole> list) {
		// TODO Auto-generated method stub
		return userRoleMapper.insert(list);
	}

	@Override
	public int delete(String userId) {
		// TODO Auto-generated method stub
		return userRoleMapper.delete(userId);
	}

	@Override
	public List<UserRole> query(String userId) {
		// TODO Auto-generated method stub
		return userRoleMapper.query(userId);
	}

}
