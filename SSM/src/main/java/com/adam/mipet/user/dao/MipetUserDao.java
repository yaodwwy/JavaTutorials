package com.adam.mipet.user.dao;

import java.util.List;
import java.util.Map;

import com.adam.mipet.user.entity.MipetUser;

public interface MipetUserDao {
	int insert(MipetUser mipetUser);
	int delete(MipetUser mipetUser);
	int update(MipetUser mipetUser);
	int updateClientId(MipetUser mipetUser);
	int updatepwd(MipetUser mipetUser);
	List<MipetUser> query(Map<String, Object> map);
	MipetUser queryLogin(Map<String, Object> map);
	MipetUser querybyid(int id);
	MipetUser querybyphone(Map<String, Object> map);
	int queryCheckRepeatPhone(Map<String, Object> map);
	int queryCount(Map<String, Object> map);
	int updateStatus(MipetUser mipetUser);
}
