package com.adam.mipet.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.common.entity.Paging;
import com.adam.mipet.user.dao.MipetUserDao;
import com.adam.mipet.user.entity.MipetUser;
import com.adam.mipet.user.service.IMipetUserService;

@Service
public class MipetUserServiceImpl implements IMipetUserService {

	@Resource
	private MipetUserDao userMapper;

	@Override
	public int insert(MipetUser mipetUser) {
		return userMapper.insert(mipetUser);
	}

	@Override
	public int delete(MipetUser mipetUser) {
		return userMapper.delete(mipetUser);
	}

	@Override
	public int update(MipetUser mipetUser) {
		return userMapper.update(mipetUser);
	}


	@Override
	public Integer queryCount(Map<String, Object> map) {
		return userMapper.queryCount(map);
	}

	@Override
	public int updatepwd(MipetUser mipetUser) {
		return userMapper.updatepwd(mipetUser);
	}

	@Override
	public Paging<MipetUser> query(Map<String, Object> map) {
		Paging<MipetUser> pagedUser = new Paging<MipetUser>();
		List<MipetUser> query = userMapper.query(map);
		pagedUser.setRows(query);
		pagedUser.setTotal(userMapper.queryCount(map));
		return pagedUser;
	}

	@Override
	public Integer queryCheckRepeatPhone(Map<String, Object> map) {
		return userMapper.queryCheckRepeatPhone(map);
	}

	@Override
	public MipetUser queryLogin(Map<String, Object> map) {
	
		return userMapper.queryLogin(map);
	}

	@Override
	public MipetUser querybyid(int id) {
	
		// TODO Auto-generated method stub
		return userMapper.querybyid(id);
	}

	@Override
	public MipetUser querybyphone(Map<String, Object> map) {
		return userMapper.querybyphone(map);
	}

	@Override
	public int updateStatus(MipetUser mipetUser) {
		return userMapper.updateStatus(mipetUser);
	}

	@Override
	public int updateClientId(MipetUser mipetUser) {
		return userMapper.updateClientId(mipetUser);
	}

}
