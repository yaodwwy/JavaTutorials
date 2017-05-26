package com.adam.mipet.goods.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.entity.Paging;
import com.adam.mipet.goods.entity.ShopsGoods;
import com.adam.mipet.goods.entity.ShopsGoodsExample;
import com.adam.mipet.goods.entity.ShopsGoodsExample.Criteria;
import com.adam.mipet.goods.service.IShopsGoodsService;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource
	IShopsGoodsService goodsService;
	
	/**
	 * 添加商品
	 * @param goods
	 * @param 
	 * @return
	 */
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String,Object> insert(ShopsGoods goods){
		try {
			int isOk = goodsService.insert(goods);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 修改商品
	 * @param goods
	 * @param 
	 * @return
	 */
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String,Object> update(ShopsGoods goods){
		try {
			int isOk = goodsService.updateByPrimaryKeySelective(goods);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 删除商品
	 * @param id
	 * @param 
	 * @return
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String,Object> del(Integer id){
		try {
			if (null == id) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			ShopsGoods hopsGoods = new ShopsGoods();
			hopsGoods.setId(id);
			hopsGoods.setDeleted(1);
			int isOk = 0;
			isOk = goodsService.updateByPrimaryKeySelective(hopsGoods);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 查询单个商品
	 * @param id
	 * @param 
	 * @return
	 */
	@RequestMapping("query.do")
	@ResponseBody
	public Map<String,Object> select(Integer id){
		try {
			if (null == id) {
				return Constant.MSG.MAP_ILLEGAL();
			}
			ShopsGoods shopsGoods = goodsService.selectByPrimaryKey(id);
			if(shopsGoods!=null){
				return Constant.MSG.RESULT_SUCCESS(shopsGoods);
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
/**
 * 查询全部商品列表
 * @param type	所属类别
 * @param page	第几页
 * @param rows	显示多少条
 * @return
 */
	@RequestMapping("queryall.do")
	@ResponseBody
	public Map<String,Object> queryall(Integer type,Integer page, Integer rows){
		if (type == null || null == page || null == rows) {
			return Constant.MSG.MAP_ILLEGAL();
		}
		try {
			int start = rows * (page - 1);
			ShopsGoodsExample shopsGoodsExample = new ShopsGoodsExample();
			shopsGoodsExample.setStart(start);
			shopsGoodsExample.setLimit(rows);
			Criteria createCriteria = shopsGoodsExample.createCriteria();
			createCriteria.andDeletedEqualTo(0);
			createCriteria.andTypesEqualTo(type);
			List<ShopsGoods> selectByExample = goodsService.selectByExample(shopsGoodsExample);
			return Constant.MSG.RESULT_SUCCESS(selectByExample);
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	/**
	 * 后台列表查询
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("querylist.do")
	@ResponseBody
	public Paging<ShopsGoods> queryList(String title,String city,String type,Integer page, Integer rows) {
		Paging<ShopsGoods> pageList = new Paging<ShopsGoods>();
		if(page == null || rows == null){
			return pageList;
		}
		ShopsGoodsExample goodsExample = new ShopsGoodsExample();
		Criteria criteria = goodsExample.createCriteria();
		criteria.andDeletedEqualTo(0);
		if (null != title && !"".equals(title)) {
			criteria.andTitleLike("%"+title+"%");
		}
		if (null != city && !"".equals(city)) {
			criteria.andPositionEqualTo(city);
		}
		if (null != type && !"".equals(type)) {
			criteria.andTypesEqualTo(Integer.parseInt(type));
		}
		int start = rows * (page - 1);
		goodsExample.setStart(start);
		goodsExample.setLimit(rows);
		List<ShopsGoods> shopsGoods = goodsService.selectByExample(goodsExample);
		pageList.setRows(shopsGoods);
		pageList.setTotal(goodsService.countByExample(goodsExample));
		return pageList;

	}
}
