package com.adam.sys.apilist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.apilist.dao.TypeDao;
import com.adam.sys.apilist.entity.ApiInfoType;
import com.adam.sys.apilist.service.IApiInfoTypeService;
@Service
public class ApiInfoTypeServiceImpl implements IApiInfoTypeService {
@Resource
private TypeDao ApiInfoTypeMapper;
	@Override
	public List<ApiInfoType> query(Map<String,Object> type) {
		// TODO Auto-generated method stub
		return ApiInfoTypeMapper.query(type);
	}

	@Override
	public int insert(ApiInfoType type) {
		// TODO Auto-generated method stub
		return ApiInfoTypeMapper.insert(type);
	}

	@Override
	public int delete(ApiInfoType type) {
		// TODO Auto-generated method stub
		return ApiInfoTypeMapper.delete(type);
	}

	@Override
	public int update(ApiInfoType type) {
		// TODO Auto-generated method stub
		return ApiInfoTypeMapper.update(type);
	}

	@Override
	public Integer updateNameSortDown(String name) {
		String precName = ApiInfoTypeMapper.queryPrecNameBySort(name);
		if(precName==null||"".equals(precName)){
			return 0;
		}else{
			ApiInfoTypeMapper.updateSortUp(precName);
			ApiInfoTypeMapper.updateSortDown(name);
			return 1;
		}
	}

	@Override
	public Integer updateNameSortUp(String name) {
		String nextName = ApiInfoTypeMapper.queryNextNameBySort(name);
		if(nextName==null||"".equals(nextName)){
			return 0;
		}else{
			ApiInfoTypeMapper.updateSortUp(name);
			ApiInfoTypeMapper.updateSortDown(nextName);
			return 1;
		}
	}

	@Override
	public List<ApiInfoType> queryWithOutBeing(String title) {
		return ApiInfoTypeMapper.queryWithOutBeing(title);
	}

}
