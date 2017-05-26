package com.adam.sys.sysfunc.service;

import java.util.List;
import java.util.Map;

import com.adam.sys.sysfunc.entity.Job;

public interface IJobService {
	List<Job> query(Map<String, Object> map);
    
    int insert(Job job);

    int delete(Job job);
    
    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
    
    int updatePrevfiretimeByClass(String jobClass);
    
    int updateStatus(Job record);
}
