package com.adam.sys.sysfunc.service;

import java.util.List;

import com.adam.sys.sysfunc.entity.DictDetail;

public interface IDictDetailService {
	
    int delete(DictDetail record);

    int insert(DictDetail record);

    int update(DictDetail record);
    
    List<DictDetail> query(String code);
    
    int queryValidateCode(DictDetail record);
    
    DictDetail queryByCodeAndType(String code, int type);

	List<DictDetail> queryList(String code);
}
