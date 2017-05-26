package com.adam.sys.apilist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.adam.sys.apilist.dao.ApiDao;
import com.adam.sys.apilist.entity.ApiEntity;
import com.adam.sys.apilist.service.IApiEntityService;
@Service
public class ApiEntityServiceImpl implements IApiEntityService {

	@Resource
	private ApiDao ApiEntityMapper;
	@Override
	public List<ApiEntity> query(Map<String, Object> map) {
		return ApiEntityMapper.query(map);
	}

	@Override
	public int insert(ApiEntity api) {
		return ApiEntityMapper.insert(api);
	}

	@Override
	public int delete(ApiEntity api) {
		return ApiEntityMapper.delete(api);
	}

	@Override
	public int update(ApiEntity api) {
		return ApiEntityMapper.update(api);
	}

	@Override
	public List<ApiEntity> queryByTittle(ApiEntity apiinfo,
			HttpServletRequest requst) {
		return ApiEntityMapper.queryByTittle(apiinfo, requst);
	}

}
