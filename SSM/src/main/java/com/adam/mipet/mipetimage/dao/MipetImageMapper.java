package com.adam.mipet.mipetimage.dao;

import com.adam.mipet.mipetimage.entity.MipetImage;
import com.adam.mipet.mipetimage.entity.MipetImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MipetImageMapper {
    int countByExample(MipetImageExample example);

    int deleteByExample(MipetImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MipetImage record);

    int insertSelective(MipetImage record);

    List<MipetImage> selectByExample(MipetImageExample example);

    MipetImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MipetImage record, @Param("example") MipetImageExample example);

    int updateByExample(@Param("record") MipetImage record, @Param("example") MipetImageExample example);

    int updateByPrimaryKeySelective(MipetImage record);

    int updateByPrimaryKey(MipetImage record);
}