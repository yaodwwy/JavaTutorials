package com.adam.mipet.goods.dao;

import com.adam.mipet.goods.entity.ShopsGoods;
import com.adam.mipet.goods.entity.ShopsGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopsGoodsMapper {
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