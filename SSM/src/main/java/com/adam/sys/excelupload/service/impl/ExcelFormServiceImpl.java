package com.adam.sys.excelupload.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.excelupload.dao.ExcelFormDao;
import com.adam.sys.excelupload.entity.ExcelForm;
import com.adam.sys.excelupload.service.IExcelFormService;

@Service
public class ExcelFormServiceImpl implements IExcelFormService {

	@Resource
	private ExcelFormDao excelFormDao;
	
	@Override
	public ExcelForm query() {
		return excelFormDao.query();
	}

	@Override
	public int update(ExcelForm excel) {
		return excelFormDao.update(excel);
	}

}
