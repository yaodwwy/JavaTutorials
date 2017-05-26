package com.adam.sys.sysfunc.dao;

import java.util.List;

import com.adam.sys.sysfunc.entity.Dict;

/**
 * 
 *
 * 类名称：DictDao
 * 类描述：
 * 创建人：Elvis
 * 修改人：Elvis
 * 修改时间：2015年6月22日 下午3:31:08
 * 修改备注：
 * @version 1.0.0
 *
 */
public interface DictDao {
    int delete(Dict record);

    int insert(Dict record);

    int update(Dict record);
    
    int queryValidateCode(String code);
    
    List<Dict> query();
    
    Dict queryById(int id);
    Dict queryByCode(String code);
}