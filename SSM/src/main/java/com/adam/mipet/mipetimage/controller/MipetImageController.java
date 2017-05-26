package com.adam.mipet.mipetimage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.entity.Paging;
import com.adam.mipet.mipetimage.entity.MipetImage;
import com.adam.mipet.mipetimage.entity.MipetImageExample;
import com.adam.mipet.mipetimage.entity.MipetImageExample.Criteria;
import com.adam.mipet.mipetimage.service.IMipetImageService;
import com.adam.mipet.util.Constant;

@Controller
@RequestMapping("mipetimage")
public class MipetImageController {
	
	@Resource
	IMipetImageService mipetImageService;
	
	@RequestMapping("insert.do")
	@ResponseBody
	public Map<String,Object> insert(MipetImage image){
		try {
			int isOk = mipetImageService.insert(image);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public Map<String,Object> delete(Integer id){
		try {
			int isOk = mipetImageService.deleteByPrimaryKey(id);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	@RequestMapping("update.do")
	@ResponseBody
	public Map<String,Object> update(MipetImage image){
		try {
			int isOk = mipetImageService.updateByPrimaryKeySelective(image);
			if (isOk > 0) {
				return Constant.MSG.RESULT_SUCCESS();
			}
			return Constant.MSG.RESULT_ERROR();
		} catch (Exception e) {
			return Constant.MSG.SYSERROR();
		}
	}
	
	@RequestMapping("query.do")
	@ResponseBody
	public Map<String,Object> query(){
		try {
			MipetImageExample mipetImageExample = new MipetImageExample();
			Criteria createCriteria = mipetImageExample.createCriteria();
			createCriteria.andDeletedEqualTo(0);
			createCriteria.andStatusEqualTo(0);
			List<MipetImage> selectList = mipetImageService.selectByExample(mipetImageExample);
			if(selectList!=null && selectList.size()>0){
				return Constant.MSG.RESULT_SUCCESS(selectList);
			}
			return Constant.MSG.RESULT_ERROR("查询失败或无数据");
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
	public Paging<MipetImage> queryList(Integer page, Integer rows) {
		Paging<MipetImage> pageList = new Paging<MipetImage>();
		if(page == null || rows == null){
			return pageList;
		}
		MipetImageExample example = new MipetImageExample();
		int start = rows * (page - 1);
		example.setStart(start);
		example.setLimit(rows);
		List<MipetImage> selectByExample = mipetImageService.selectByExample(example);
		pageList.setRows(selectByExample);
		pageList.setTotal(mipetImageService.countByExample(example));
		return pageList;

	}
	
}
