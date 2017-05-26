package com.adam.sys.sysfunc.service;

import java.util.List;

import com.adam.sys.sysfunc.entity.Dict;

public interface IDictService {
    int delete(Dict record);

    int insert(Dict record);

    int update(Dict record);
    
    int queryValidateCode(String code);
    
    List<Dict> query();
}
