package com.adam.sys.excelupload.dao;

import com.adam.sys.excelupload.entity.ExcelForm;

public interface ExcelFormDao {
	
	public ExcelForm query();

	public int update(ExcelForm excel);
	
}
