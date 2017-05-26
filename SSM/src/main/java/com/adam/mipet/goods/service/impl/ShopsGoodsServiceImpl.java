package com.adam.mipet.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.mipet.goods.dao.ShopsGoodsMapper;
import com.adam.mipet.goods.entity.ShopsGoods;
import com.adam.mipet.goods.entity.ShopsGoodsExample;
import com.adam.mipet.goods.service.IShopsGoodsService;
@Service
public class ShopsGoodsServiceImpl implements IShopsGoodsService {
	@Resource
	private ShopsGoodsMapper shopsgoods;

	@Override
	public int countByExample(ShopsGoodsExample example) {
	
		// TODO Auto-generated method stub
		return shopsgoods.countByExample(example);
	}

	@Override
	public int deleteByExample(ShopsGoodsExample example) {
	
		// TODO Auto-generated method stub
		return shopsgoods.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return shopsgoods.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ShopsGoods record) {
	
		// TODO Auto-generated method stub
		return shopsgoods.insert(record);
	}

	@Override
	public int insertSelective(ShopsGoods record) {
	
		// TODO Auto-generated method stub
		return shopsgoods.insertSelective(record);
	}

	@Override
	public List<ShopsGoods> selectByExample(ShopsGoodsExample example) {
	
		// TODO Auto-generated method stub
		return shopsgoods.selectByExample(example);
	}

	@Override
	public ShopsGoods selectByPrimaryKey(Integer id) {
	
		// TODO Auto-generated method stub
		return shopsgoods.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(ShopsGoods record, ShopsGoodsExample example) {
	
		// TODO Auto-generated method stub
		return shopsgoods.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(ShopsGoods record, ShopsGoodsExample example) {
	
		// TODO Auto-generated method stub
		return shopsgoods.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(ShopsGoods record) {
	
		// TODO Auto-generated method stub
		return shopsgoods.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ShopsGoods record) {
	
		// TODO Auto-generated method stub
		return shopsgoods.updateByPrimaryKey(record);
	}

}
