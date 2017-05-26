package com.adam.mipet.foster.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.foster.dao.FosterStoreMapper;
import com.adam.mipet.foster.entity.FosterStore;
import com.adam.mipet.foster.entity.FosterStoreExample;
import com.adam.mipet.foster.service.IFosterService;

@Service
public class FosterServiceImpl implements IFosterService {
	@Resource
	private FosterStoreMapper foster;

	@Override
	public int countByExample(FosterStoreExample example) {
	
		// TODO Auto-generated method stub
		return foster.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return foster.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FosterStore record) {
	
		// TODO Auto-generated method stub
		return foster.insert(record);
	}

	@Override
	public List<FosterStore> selectByExample(FosterStoreExample example) {
	
		// TODO Auto-generated method stub
		return foster.selectByExample(example);
	}

	@Override
	public FosterStore selectByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return foster.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(FosterStore record, FosterStoreExample example) {
	
		// TODO Auto-generated method stub
		return foster.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(FosterStore record, FosterStoreExample example) {
	
		// TODO Auto-generated method stub
		return foster.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(FosterStore record) {
	
		// TODO Auto-generated method stub
		return foster.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(FosterStore record) {
	
		// TODO Auto-generated method stub
		return foster.updateByPrimaryKey(record);
	}

	
}
