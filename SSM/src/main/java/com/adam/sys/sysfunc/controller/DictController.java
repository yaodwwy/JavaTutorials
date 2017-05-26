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
import com.adam.sys.sysfunc.entity.Dict;
import com.adam.sys.sysfunc.service.IDictService;

/**
 *
 * 类名称：DictController
 *
 */
@Controller
@RequestMapping("/dict")
public class DictController {

	@Resource private IDictService dictService;
	
	@ResponseBody
	@RequestMapping("query")
	public List<Dict> query(){
		return dictService.query();
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public void insert(HttpServletResponse response,Dict dict)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			if(dictService.queryValidateCode(dict.getCode())>0){
				response.getWriter().print(JSON.toJSON(Constant.MSG.ERR_COMMON("编码已存在")));
			}else{
				dictService.insert(dict);
				response.getWriter().print(JSON.toJSON(Constant.MSG.SAVE_OK()));
			}
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.SAVE_ERROR()));
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public void update(HttpServletResponse response,Dict dict)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			dictService.update(dict);
			response.getWriter().print(JSON.toJSON(Constant.MSG.UPD_OK()));
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.UPD_ERROR()));
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(Integer id){
		try {
			Dict dict = new Dict();
			dict.setId(id);
			dictService.delete(dict);
			return Constant.MSG.DEL_OK();
		} catch (Exception e) {
			return Constant.MSG.DEL_ERROR();
		}
	}
	
}
