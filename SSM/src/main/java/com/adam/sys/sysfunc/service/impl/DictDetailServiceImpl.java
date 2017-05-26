package com.adam.sys.sysfunc.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.sysfunc.dao.DictDao;
import com.adam.sys.sysfunc.dao.DictDetailDao;
import com.adam.sys.sysfunc.entity.Dict;
import com.adam.sys.sysfunc.entity.DictDetail;
import com.adam.sys.sysfunc.service.IDictDetailService;

@Service
public class DictDetailServiceImpl implements IDictDetailService {
	
	private final static String PREFIX = "bd.dict.detail.code.";
	
	@Resource
	private DictDetailDao detailDao;
	
	@Resource
	private DictDao dictDao;
	
	@Override
	public int delete(DictDetail record) {
	
		// TODO Auto-generated method stub
		record = detailDao.queryById(record.getId());
		return detailDao.delete(record);
	}
	
	@Override
	public int insert(DictDetail record) {
	
		// TODO Auto-generated method stub
		Dict dict = dictDao.queryByCode(record.getCode());
		if (dict != null) {
			record.setCodeName(dict.getName());
		}
		int result = detailDao.insert(record);
		return result;
	}
	
	@Override
	public int update(DictDetail record) {
	
		// TODO Auto-generated method stub
		Dict dict = dictDao.queryByCode(record.getCode());
		if (dict != null) {
			record.setCodeName(dict.getName());
		}
		int result = detailDao.update(record);
		return result;
	}
	
	@Override
	public List<DictDetail> query(String code) {
	
		// TODO Auto-generated method stub
		List<DictDetail> details = details = detailDao.query(code);
		Collections.sort(details, new Comparator<DictDetail>() {
			
			@Override
			public int compare(DictDetail o1, DictDetail o2) {
			
				return o1.getType() - o2.getType();
			}
		});
		return details;
	}
	
	@Override
	public int queryValidateCode(DictDetail record) {
	
		// TODO Auto-generated method stub
		return detailDao.queryValidateCode(record);
	}
	
	@Override
	public DictDetail queryByCodeAndType(String code, int type) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("type", type);
		return detailDao.queryByCodeAndType(map);
	}
	
	@Override
	public List<DictDetail> queryList(String code) {
	
		List<DictDetail> details = details = detailDao.queryList(code);
		return details;
	}
}
