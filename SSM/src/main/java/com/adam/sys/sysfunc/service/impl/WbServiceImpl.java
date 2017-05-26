package com.adam.sys.sysfunc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.sysfunc.dao.WbDao;
import com.adam.sys.sysfunc.entity.Wb;
import com.adam.sys.sysfunc.service.IWbService;

@Service
public class WbServiceImpl implements
		IWbService {
	@Resource
	private WbDao wbDao;
	
	@Override
	public int delete(Wb wb) {
		// TODO Auto-generated method stub
		return wbDao.delete(wb);
	}

	@Override
	public int update(Wb wb) {
		// TODO Auto-generated method stub
		return wbDao.update(wb);
	}

	@Override
	public int insert(Wb wb) {
		// TODO Auto-generated method stub
		int result = wbDao.insert(wb);
		return result;
	}

	@Override
	public Integer validateDataType(String dataType) {
		// TODO Auto-generated method stub
		return wbDao.validateDataType(dataType);
	}

	@Override
	public List<Wb> queryAll() {
		// TODO Auto-generated method stub
		return wbDao.queryAll();
	}

	@Override
	public List<Wb> query(Wb wb) {
		// TODO Auto-generated method stub
		return wbDao.query(wb);
	}

	@Override
	public Wb queryByDataType(String dataType) {
		// TODO Auto-generated method stub
		Wb wb = wbDao.queryByDataType(dataType);
		return wb;
	}
}
