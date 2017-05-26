package com.adam.sys.sysfunc.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.sysfunc.dao.DictDao;
import com.adam.sys.sysfunc.entity.Dict;
import com.adam.sys.sysfunc.service.IDictService;

/**
 * 
 *
 * 类名称：DictServiceImpl
 * 类描述：
 * 创建人：Elvis
 * 修改人：Elvis
 * 修改时间：2015年6月22日 上午11:33:14
 * 修改备注：
 * @version 1.0.0
 *
 */
@Service
public class DictServiceImpl implements IDictService {
	
    private final static String PREFIX = "bd.dict";
	
	@Resource
	private DictDao dictDao;

	@Override
	public int delete(Dict record) {
		return dictDao.delete(record);
	}

	@Override
	public int insert(Dict record) {
		int result = dictDao.insert(record);
		return result;
	}

	@Override
	public int update(Dict record) {
		// TODO Auto-generated method stub
		return dictDao.update(record);
	}

	@Override
	public int queryValidateCode(String code) {
		// TODO Auto-generated method stub
		return dictDao.queryValidateCode(code);
	}

	@Override
	public List<Dict> query() {
		List<Dict> dicts =  dictDao.query();
		Collections.sort(dicts,new Comparator<Dict>() {

			@Override
			public int compare(Dict o1, Dict o2) {
				return o1.getId() - o2.getId();
			}
			
		});
		return dicts;
	}

}
