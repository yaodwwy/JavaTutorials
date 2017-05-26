package com.adam.sys.userfunc.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.common.entity.Paging;
import com.adam.sys.userfunc.dao.UserDao;
import com.adam.sys.userfunc.entity.User;
import com.adam.sys.userfunc.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserDao userMapper;
	
	@Override
	public int delete(User record) {
		
		return userMapper.delete(record);
	}

	@Override
	public int insert(User record) {
		int result = userMapper.insert(record);
		return result;
	}

	@Override
	public User queryLogin(Map<String, Object> map) {
		return userMapper.queryLogin(map);
	}

	@Override
	public int update(User record) {
		return userMapper.update(record);
	}
	
	@Override
	public Paging<User> queryAll(Map<String,Object> map) {
		Paging<User> paging = new Paging<User>();
		paging.setRows(userMapper.queryAll(map));
		paging.setTotal(userMapper.queryAllCount());
		return paging;
	}

	@Override
	public int queryCheckUserName(String username) {
		// TODO Auto-generated method stub
		return userMapper.queryCheckUserName(username);
	}

	@Override
	public void updatepwd(User user) {
		userMapper.updatepwd(user);
	}


}
