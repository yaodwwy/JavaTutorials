package com.adam.sys.apilist.dao;

import java.util.List;
import java.util.Map;

import com.adam.sys.apilist.entity.ApiInfoType;
/**
 * 
 * 类名称：TypeDao.java
 * 类描述：对表:t_site_html_anchor 进行操作 锚点链接
 * 创建人：yaoyanbing
 * 修改人：yaoyanbing
 * 修改时间： 2016年1月19日 上午10:08:47 
 * 修改备注：
 */
public interface TypeDao {
	
	List<ApiInfoType> query(Map<String, Object> type);
	List<ApiInfoType> queryWithOutBeing(String title);

	int insert(ApiInfoType type);

	int delete(ApiInfoType type);

	int update(ApiInfoType type);
	 
	String queryPrecNameBySort(String name);
	String queryNextNameBySort(String name);
	int updateSortUp(String name);
	int updateSortDown(String nextName);

}
