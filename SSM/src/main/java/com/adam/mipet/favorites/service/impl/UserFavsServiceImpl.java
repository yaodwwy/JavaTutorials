package com.adam.mipet.favorites.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.favorites.dao.IUserFavsDao;
import com.adam.mipet.favorites.entity.UserFavs;
import com.adam.mipet.favorites.entity.UserFavsExample;
import com.adam.mipet.favorites.service.IUserFavsService;

@Service
public class UserFavsServiceImpl implements IUserFavsService {
	
	@Resource
	private IUserFavsDao userFavsDao; 
	
	@Override
	public int countByExample(UserFavsExample example) {
		return userFavsDao.countByExample(example);
	}
	
	@Override
	public int deleteByExample(UserFavsExample example) {
		return userFavsDao.deleteByExample(example);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		return userFavsDao.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(UserFavs record) {
	
		return userFavsDao.insert(record);
	}
	
	@Override
	public int insertSelective(UserFavs record) {
	
		return userFavsDao.insertSelective(record);
	}
	
	@Override
	public List<UserFavs> selectByExample(UserFavsExample example) {
	
		return userFavsDao.selectByExample(example);
	}
	
	@Override
	public UserFavs selectByPrimaryKey(Integer id) {
	
		return userFavsDao.selectByPrimaryKey(id);
	}

	@Override
	public List<UserFavs> selectMyCollections(UserFavsExample example) {
	
		return userFavsDao.selectMyCollections(example);
	}
	
}
