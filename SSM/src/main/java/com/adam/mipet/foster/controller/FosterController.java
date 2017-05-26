package com.adam.mipet.foster.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.entity.Paging;
import com.adam.common.util.MybatisUtil;
import com.adam.mipet.foster.entity.FosterStore;
import com.adam.mipet.foster.entity.FosterStoreExample;
import com.adam.mipet.foster.service.IFosterService;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("/fos")
public class FosterController {
	@Resource
	IFosterService fosterService;

	/**
	 * 添加店铺
	 * 
	 * @param sore
	 * @param
	 * @return
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String, Object> add(FosterStore sore) {
		try {
			int isOk = fosterService.insert(sore);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 修改店铺
	 * 
	 * @param sore
	 * @param
	 * @return
	 */
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String, Object> update(FosterStore sore) {
		try {
			int isOk = fosterService.updateByPrimaryKeySelective(sore);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 删除店铺
	 * 
	 * @param id
	 * @param
	 * @return
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String, Object> del(Integer id) {
		try {
			if (null == id) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			FosterStore fosterStore = new FosterStore();
			fosterStore.setId(id);
			fosterStore.setDeleted(1);
			int isOk = 0;
			isOk = fosterService.updateByPrimaryKeySelective(fosterStore);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	/**
	 * 查询单个店铺
	 * 
	 * @param id
	 * @param
	 * @return
	 */
	@RequestMapping("query.do")
	@ResponseBody
	public Map<String, Object> select(Integer id) {
		try {
			if (null == id) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			FosterStore store = fosterService.selectByPrimaryKey(id);
			if(store!=null){
				return Constant.MSG.RESULT_SUCCESS(store);
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}

	/**
	 * 查询全部店铺列表
	 * @param address
	 * @return
	 */
	@RequestMapping("queryall.do")
	@ResponseBody
	public Map<String, Object> queryall(String city ,int page, int rows) {
		int start = rows * (page - 1);
		try {
			FosterStoreExample fosterStoreExample = new FosterStoreExample();
			fosterStoreExample.setStart(start);
			fosterStoreExample.setLimit(rows);
			FosterStoreExample.Criteria createCriteria = fosterStoreExample.createCriteria();
			if(city!=null && city!="" && city!="null"){
				createCriteria.andCityLike("%"+city+"%");
			}
			createCriteria.andDeletedEqualTo(0);
			List<FosterStore> selectByExample = fosterService.selectByExample(fosterStoreExample);
			
			return Constant.MSG.RESULT_SUCCESS(selectByExample);
			
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 查询全部店铺列表
	 * @param address
	 * @return
	 */
	@RequestMapping("querylist.do")
	@ResponseBody
	public Paging<FosterStore> querylist(String city,String name,String services, Integer page, Integer rows, String sort,String order) {
		Paging<FosterStore> pageList = new Paging<FosterStore>();
		if(page == null || rows == null){
			return pageList;
		}
		FosterStoreExample fosterStoreExample = new FosterStoreExample();
		FosterStoreExample.Criteria criteria = fosterStoreExample.createCriteria();
		criteria.andDeletedEqualTo(0);
		if (null != city && !"".equals(city)) {
			criteria.andCityLike("%"+city+"%");
		}
		if (null != name && !"".equals(name)) {
			criteria.andNameLike("%"+name+"%");
		}
		if (null != services && !"".equals(services)) {
			criteria.andItemsLike("%"+services+"%");
		}
		int start = rows * (page - 1);
		if(sort != null && order != null){
			fosterStoreExample.setOrder(order);
			sort = MybatisUtil.getSQLName(sort);
			fosterStoreExample.setSort(sort);
		}
		fosterStoreExample.setStart(start);
		fosterStoreExample.setLimit(rows);
		List<FosterStore> fosterStore = fosterService.selectByExample(fosterStoreExample);
		pageList.setRows(fosterStore);
		pageList.setTotal(fosterService.countByExample(fosterStoreExample));
		return pageList;
	}
}
