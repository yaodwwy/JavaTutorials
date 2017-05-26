package com.adam.sys.sysfunc.dao;

import java.util.List;

import com.adam.sys.sysfunc.entity.Wb;

public interface WbDao {
    int delete(Wb wb);
    int update(Wb wb);
    int insert(Wb wb);
    Integer validateDataType(String dataType);
    List<Wb> query(Wb wb);
    List<Wb> queryAll();
    Wb queryByDataType(String dataType);
}