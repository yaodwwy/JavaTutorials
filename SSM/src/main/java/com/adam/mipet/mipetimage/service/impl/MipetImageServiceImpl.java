package com.adam.mipet.mipetimage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.mipetimage.dao.MipetImageMapper;
import com.adam.mipet.mipetimage.entity.MipetImage;
import com.adam.mipet.mipetimage.entity.MipetImageExample;
import com.adam.mipet.mipetimage.service.IMipetImageService;

@Service
public class MipetImageServiceImpl implements IMipetImageService {
	
	@Resource
	private MipetImageMapper MipetImageMapper;
	
	
	@Override
	public int countByExample(MipetImageExample example) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.countByExample(example);
	}
	
	@Override
	public int deleteByExample(MipetImageExample example) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.deleteByExample(example);
	}
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(MipetImage record) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.insert(record);
	}
	
	@Override
	public int insertSelective(MipetImage record) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.insertSelective(record);
	}
	
	@Override
	public List<MipetImage> selectByExample(MipetImageExample example) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.selectByExample(example);
	}
	
	@Override
	public MipetImage selectByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByExampleSelective(MipetImage record, MipetImageExample example) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.updateByExampleSelective(record, example);
	}
	
	@Override
	public int updateByExample(MipetImage record, MipetImageExample example) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.updateByExample(record, example);
	}
	
	@Override
	public int updateByPrimaryKeySelective(MipetImage record) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int updateByPrimaryKey(MipetImage record) {
	
		// TODO Auto-generated method stub
		return MipetImageMapper.updateByPrimaryKey(record);
	}
}
