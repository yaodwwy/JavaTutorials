package com.adam.sys.apilist.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.adam.sys.apilist.entity.ApiEntity;

/**
 * 
 * 类名称：ApiEntityService.java 类描述：接口文档服务 创建人：yaoyanbing 修改人：yaoyanbing 修改时间：
 * 2016年1月19日 上午10:40:33 修改备注：
 */
public interface IApiEntityService {
	public List<ApiEntity> queryByTittle(ApiEntity apiinfo,
                                         HttpServletRequest requst);

	List<ApiEntity> query(Map<String, Object> map);

	int insert(ApiEntity api);

	int delete(ApiEntity api);

	int update(ApiEntity api);

}
