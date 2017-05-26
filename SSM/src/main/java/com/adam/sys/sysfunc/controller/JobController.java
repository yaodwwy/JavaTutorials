package com.adam.sys.sysfunc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adam.common.constant.Constant;
import com.adam.sys.sysfunc.entity.DictDetail;
import com.adam.sys.sysfunc.entity.Job;
import com.adam.sys.sysfunc.service.IDictDetailService;
import com.adam.sys.sysfunc.service.IJobService;
import com.adam.sys.userfunc.entity.User;

/**
 *
 * 类名称：JobController
 * 类描述：
 * 创建人：Elvis
 * 修改人：Elvis
 * 修改时间：2015年12月21日 上午9:11:19
 * 修改备注：
 * @version 1.0.0
 *
 */

@Controller
@RequestMapping("/job")
@SuppressWarnings("all")
public class JobController {

	@Resource
	private IJobService jobService;
	@Resource
	private IDictDetailService dictService;
	
	@RequestMapping("query")
	@ResponseBody
	public List<Job> query(Job job){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("jobName",job.getJobName());
		List<Job> list = jobService.query(map);
		for (Job job2 : list) {
			DictDetail dict = dictService.queryByCodeAndType("job_status",Integer.parseInt(job2.getJobStatus()));
			if(dict!=null){
				job2.setJobStatusname(dict.getDescription());
			}
		}
		return list;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public Map<String,Object> insert(Job job,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			job.setCreator(user.getUsername());
			job.setUpdator(user.getUsername());
			job.setJobStatus("1");
			jobService.insert(job);
			
			return Constant.MSG.SAVE_OK();
		} catch (Exception e) {
			return Constant.MSG.SAVE_ERROR();
		}
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Map<String,Object> update(Job job,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			job.setUpdator(user.getUsername());
			jobService.updateByPrimaryKey(job);
			return Constant.MSG.UPD_OK();
		} catch (Exception e) {
			return Constant.MSG.UPD_ERROR();
		}
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(Job job,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			job.setUpdator(user.getUsername());
			job.setJobStatus("4");
			jobService.updateStatus(job);
			return Constant.MSG.DEL_OK();
		} catch (Exception e) {
			return Constant.MSG.DEL_ERROR();
		}
	}
	
	@ResponseBody
	@RequestMapping("change_status")
	public Map<String,Object> change_status(Job job,HttpSession session){
		try {
			User user = (User)session.getAttribute("admin");
			if(user==null){
				return Constant.MSG.MAP_SESSIONERR();
			}
			job.setUpdator(user.getUsername());
			jobService.updateStatus(job);
			return Constant.MSG.OK_COMMON("操作成功");	
		} catch (Exception e) {
			// TODO: handle exception
			return Constant.MSG.ERR_COMMON("操作失败");
		}
		
		
	}
	
}
