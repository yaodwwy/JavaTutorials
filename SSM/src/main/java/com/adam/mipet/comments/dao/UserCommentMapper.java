package com.adam.mipet.comments.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adam.mipet.comments.entity.UserComment;
import com.adam.mipet.comments.entity.UserCommentExample;

public interface UserCommentMapper {
	int countByExample(UserCommentExample example);

    int deleteByExample(UserCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserComment record);

    int insertSelective(UserComment record);

    List<UserComment> selectByExample(UserCommentExample example);

    UserComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserComment record, @Param("example") UserCommentExample example);

    int updateByExample(@Param("record") UserComment record, @Param("example") UserCommentExample example);

    int updateByPrimaryKeySelective(UserComment record);

    int updateByPrimaryKey(UserComment record);
    
    List<UserComment> queryUserCommentListById(UserCommentExample example);
}