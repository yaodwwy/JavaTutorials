package com.adam.sys.userfunc.dao;

import java.util.List;
import java.util.Map;

import com.adam.sys.userfunc.entity.User;


public interface UserDao {
    int delete(User record);

    int insert(User record);

    User queryLogin(Map<String, Object> map);

    int update(User record);

    List<User> queryAll(Map<String, Object> map);
    
    int queryAllCount();
    
    int queryCheckUserName(String username);
    
    void updatepwd(User user);
}