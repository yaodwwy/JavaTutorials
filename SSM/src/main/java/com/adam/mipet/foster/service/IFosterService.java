package com.adam.mipet.foster.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adam.mipet.foster.entity.FosterStore;
import com.adam.mipet.foster.entity.FosterStoreExample;


public interface IFosterService {
    int countByExample(FosterStoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FosterStore record);

    List<FosterStore> selectByExample(FosterStoreExample example);

    FosterStore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FosterStore record, @Param("example") FosterStoreExample example);

    int updateByExample(@Param("record") FosterStore record, @Param("example") FosterStoreExample example);

    int updateByPrimaryKeySelective(FosterStore record);

    int updateByPrimaryKey(FosterStore record);
}
