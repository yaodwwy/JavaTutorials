package com.adam.sys.userfunc.service;

import java.util.List;
import java.util.Map;

import com.adam.sys.userfunc.entity.Menu;

public interface IMenuService {
	
	String queryNameByid(Integer id);
	
	List<Menu> queryMainMenu(Map<String, Object> map);
	
	public Integer updateMenuSortDown(String menuId);

	public Integer updateMenuSortUp(String menuId);
	
    int delete(Menu record);

    int insert(Menu record);

    int update(Menu record);
    
    List<Menu> queryMenuY(Map<String, Object> map);
    
    List<Menu> queryMenuN(Map<String, Object> map);
    
    List<Menu> queryMenuByParentId(int parentId);
    
    String queryParentMenuName(int menuId);
    
}
