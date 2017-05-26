package com.adam.sys.sysfunc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.sysfunc.dao.JobDao;
import com.adam.sys.sysfunc.entity.Job;
import com.adam.sys.sysfunc.service.IJobService;
@Service
public class JobServiceImpl implements IJobService {

	@Resource
	private JobDao jobDao;
	
	@Override
	public List<Job> query(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return jobDao.query(map);
	}

	@Override
	public int insert(Job job) {
		// TODO Auto-generated method stub
		return jobDao.insert(job);
	}

	@Override
	public int delete(Job job) {
		// TODO Auto-generated method stub
		return jobDao.delete(job);
	}

	@Override
	public int updateByPrimaryKeySelective(Job record) {
		// TODO Auto-generated method stub
		return jobDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Job record) {
		// TODO Auto-generated method stub
		return jobDao.updateByPrimaryKey(record);
	}

	@Override
	public int updatePrevfiretimeByClass(String jobClass) {
		// TODO Auto-generated method stub
		return jobDao.updatePrevfiretimeByClass(jobClass);
	}

	@Override
	public int updateStatus(Job record) {
		// TODO Auto-generated method stub
		return jobDao.updateStatus(record);
	}

}
