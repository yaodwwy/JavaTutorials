package com.adam.sys.userfunc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adam.sys.userfunc.dao.MenuDao;
import com.adam.sys.userfunc.entity.Menu;
import com.adam.sys.userfunc.service.IMenuService;


@Service
public class MenuServiceImpl implements IMenuService {

	@Resource
	private MenuDao menuMapper;
	
	@Override
	public Integer updateMenuSortDown(String menuId) {
		// TODO Auto-generated method stub
		String nextMenuId = menuMapper.queryNextMenuBySort(menuId);
		if(nextMenuId==null||"".equals(nextMenuId)){
			return 0;
		}else{
			menuMapper.updateSortUp(menuId);
			menuMapper.updateSortDown(nextMenuId);
			return 1;
		}
	}

	@Override
	public Integer updateMenuSortUp(String menuId) {
		// TODO Auto-generated method stub
		String precMenuId = menuMapper.queryPrecMenuBySort(menuId);
		if(precMenuId==null||"".equals(precMenuId)){
			return 0;
		}else{
			menuMapper.updateSortUp(precMenuId);
			menuMapper.updateSortDown(menuId);
			return 1;
		}
	}
	
	@Override
	public List<Menu> queryMainMenu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuMapper.queryMainMenu(map);
	}

	@Override
	public int delete(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.delete(record);
	}

	@Override
	public int insert(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.insert(record);
	}

	@Override
	public int update(Menu record) {
		// TODO Auto-generated method stub
		return menuMapper.update(record);
	}

	@Override
	public List<Menu> queryMenuY(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuMapper.queryMenuY(map);
	}

	@Override
	public List<Menu> queryMenuN(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return menuMapper.queryMenuN(map);
	}

	@Override
	public List<Menu> queryMenuByParentId(int parentId) {
		// TODO Auto-generated method stub
		return menuMapper.queryMenuByParentId(parentId);
	}

	@Override
	public String queryParentMenuName(int menuId) {
		// TODO Auto-generated method stub
		return menuMapper.queryParentMenuName(menuId);
	}

	@Override
	public String queryNameByid(Integer id) {
		// TODO Auto-generated method stub
		return menuMapper.queryNameByid(id);
	}

}
