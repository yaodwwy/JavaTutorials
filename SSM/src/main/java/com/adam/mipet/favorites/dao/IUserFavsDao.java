package com.adam.mipet.favorites.dao;

import java.util.List;

import com.adam.mipet.favorites.entity.UserFavs;
import com.adam.mipet.favorites.entity.UserFavsExample;

public interface IUserFavsDao {
    int countByExample(UserFavsExample example);

    int deleteByExample(UserFavsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserFavs record);

    int insertSelective(UserFavs record);

    List<UserFavs> selectByExample(UserFavsExample example);

    UserFavs selectByPrimaryKey(Integer id);
    
    List<UserFavs> selectMyCollections(UserFavsExample example);
}