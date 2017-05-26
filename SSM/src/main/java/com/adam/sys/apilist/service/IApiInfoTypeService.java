package com.adam.sys.apilist.service;

import java.util.List;
import java.util.Map;

import com.adam.sys.apilist.entity.ApiInfoType;
/**
 * 
 * 类名称：ApiInfoTypeService.java
 * 类描述：接口文档类型(锚点)服务
 * 创建人：yaoyanbing
 * 修改人：yaoyanbing
 * 修改时间： 2016年1月19日 上午10:38:56 
 * 修改备注：
 */
public interface IApiInfoTypeService {
	List<ApiInfoType> query(Map<String, Object> type);
	List<ApiInfoType> queryWithOutBeing(String title);
	

	int insert(ApiInfoType type);

	int delete(ApiInfoType type);

	int update(ApiInfoType type);
	
	public Integer updateNameSortDown(String name);
	public Integer updateNameSortUp(String name);

}
