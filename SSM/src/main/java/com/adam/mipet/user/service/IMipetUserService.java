package com.adam.mipet.user.service;

import java.util.Map;

import com.adam.common.entity.Paging;
import com.adam.mipet.user.entity.MipetUser;

public interface IMipetUserService {
	int insert(MipetUser mipetUser);
	int delete(MipetUser mipetUser);
	int update(MipetUser mipetUser);
	int updateClientId(MipetUser mipetUser);
	int updatepwd(MipetUser mipetUser);
	Paging<MipetUser> query(Map<String, Object> map);
	MipetUser queryLogin(Map<String, Object> map);
	MipetUser querybyid(int id);
	MipetUser querybyphone(Map<String, Object> map);
	Integer queryCount(Map<String, Object> map);
	Integer queryCheckRepeatPhone(Map<String, Object> map);
	int updateStatus(MipetUser mipetUser);
}
