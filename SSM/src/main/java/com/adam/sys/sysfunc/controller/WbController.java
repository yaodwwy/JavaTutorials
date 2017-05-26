package com.adam.sys.sysfunc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.constant.Constant;
import com.adam.sys.sysfunc.entity.Wb;
import com.adam.sys.sysfunc.service.IWbService;
import com.adam.sys.userfunc.entity.User;
/**
 * 
 *
 * 类名称：WbController
 * 类描述：
 * 创建人：Elvis
 * 修改人：Elvis
 * 修改时间：2015年12月21日 下午9:10:34
 * 修改备注：
 * @version 1.0.0
 *
 */
@Controller
@RequestMapping("wb")
public class WbController {

	@Resource
	private IWbService wbService;
	
	@RequestMapping("query")
	@ResponseBody
	public List<Wb> query(Wb wb){
		return wbService.query(wb);
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public Map<String,Object> insert(HttpSession session,Wb wb){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			
			if(this.wbService.validateDataType(wb.getDataType())>0){
				return Constant.MSG.ERR_COMMON("类型重复");
			}
			
			wb.setCreator(user.getUsername());
			wb.setUpdator(user.getUsername());
			wbService.insert(wb);
			return Constant.MSG.SAVE_OK();
		} catch (Exception e) {
			return Constant.MSG.SAVE_ERROR();
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(HttpSession session,Wb wb){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			wb.setUpdator(user.getUsername());
			wbService.update(wb);
			return Constant.MSG.SAVE_OK();
		} catch (Exception e) {
			return Constant.MSG.SAVE_ERROR();
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(HttpSession session,Wb wb){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			wb.setUpdator(user.getUsername());
			wbService.delete(wb);
			return Constant.MSG.DEL_OK();
		} catch (Exception e) {
			return Constant.MSG.DEL_ERROR();
		}
	}
	
}
