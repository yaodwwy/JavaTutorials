package com.adam.sys.sysfunc.dao;

import java.util.List;
import java.util.Map;

import com.adam.sys.sysfunc.entity.DictDetail;

/**
 * 
 *
 * 类名称：DictDetailDao 类描述： 创建人：Elvis 修改人：Elvis 修改时间：2015年6月22日 下午3:31:14 修改备注：
 * 
 * @version 1.0.0
 *
 */
public interface DictDetailDao {
	int delete(DictDetail record);

	int insert(DictDetail record);

	int update(DictDetail record);

	List<DictDetail> query(String code);
	List<DictDetail> queryList(String code);

	int queryValidateCode(DictDetail record);

	List<DictDetail> findAll();

	DictDetail queryByCodeAndType(Map<String, Object> map);

	DictDetail queryById(int id);

}