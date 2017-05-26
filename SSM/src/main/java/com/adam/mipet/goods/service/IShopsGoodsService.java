package com.adam.mipet.goods.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adam.mipet.goods.entity.ShopsGoods;
import com.adam.mipet.goods.entity.ShopsGoodsExample;

public interface IShopsGoodsService {
	 int countByExample(ShopsGoodsExample example);

	    int deleteByExample(ShopsGoodsExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(ShopsGoods record);

	    int insertSelective(ShopsGoods record);

	    List<ShopsGoods> selectByExample(ShopsGoodsExample example);

	    ShopsGoods selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") ShopsGoods record, @Param("example") ShopsGoodsExample example);

	    int updateByExample(@Param("record") ShopsGoods record, @Param("example") ShopsGoodsExample example);

	    int updateByPrimaryKeySelective(ShopsGoods record);

	    int updateByPrimaryKey(ShopsGoods record);
}
