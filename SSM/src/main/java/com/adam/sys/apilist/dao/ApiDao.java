package com.adam.sys.apilist.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.adam.sys.apilist.entity.ApiEntity;

public interface ApiDao {
	public List<ApiEntity> queryByTittle(ApiEntity apiinfo,
                                         HttpServletRequest requst);
	
	List<ApiEntity> query(Map<String, Object> map);

	int insert(ApiEntity api);

	int delete(ApiEntity api);

	int update(ApiEntity api);

}
