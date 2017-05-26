package com.adam.sys.sysfunc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.adam.common.constant.Constant;
import com.adam.sys.sysfunc.entity.DictDetail;
import com.adam.sys.sysfunc.service.IDictDetailService;


@Controller
@RequestMapping("/dictdetail")
@SuppressWarnings("all")
public class DictDetailController {

	@Resource
	private IDictDetailService ddService;
	
	@RequestMapping("query")
	@ResponseBody
	public List<DictDetail> query(String code){
		return ddService.query(code);
	}
	
	@RequestMapping("queryList")
	@ResponseBody
	public List<DictDetail> queryList(String code){
		return ddService.queryList(code);
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(HttpServletResponse response,DictDetail dd)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			if(ddService.queryValidateCode(dd)>0){
				response.getWriter().print(JSON.toJSON(Constant.MSG.ERR_COMMON("编码重复")));
			}else{
				ddService.insert(dd);
				response.getWriter().print(JSON.toJSON(Constant.MSG.SAVE_OK()));
			}
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.SAVE_ERROR()));
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public void update(HttpServletResponse response,DictDetail dd)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			ddService.update(dd);
			response.getWriter().print(JSON.toJSON(Constant.MSG.UPD_OK()));
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.UPD_ERROR()));
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id){
		try {
			DictDetail dd = new DictDetail();
			dd.setId(id);
			ddService.delete(dd);
			return Constant.MSG.DEL_OK();
		} catch (Exception e) {
			return Constant.MSG.DEL_ERROR();
		}
	}
	
}
