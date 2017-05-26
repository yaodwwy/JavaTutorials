package com.adam.sys.userfunc.controller;

import com.alibaba.fastjson.JSON;
import com.adam.common.constant.Constant;
import com.adam.sys.userfunc.entity.Menu;
import com.adam.sys.userfunc.entity.Tree;
import com.adam.sys.userfunc.entity.User;
import com.adam.sys.userfunc.service.IMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/menu")
@SuppressWarnings("all")
public class MenuController {

	@Resource
	private IMenuService menuService;
	
	/***
	 * topage：(所有页面跳转都经过这个方法)
	 */
	@RequestMapping("topage")
	public String topage(Model m,String url,String parm1,String parm2,String parm3){
		m.addAttribute("parm1",parm1);
		m.addAttribute("parm2",parm2);
		m.addAttribute("parm3",parm3);
		return url;
	}
	
	/**
	 *  获取主页面用户菜单
	 */
	@ResponseBody
	@RequestMapping("queryMainMenu")
	public  List<Tree> queryMainMenu(HttpSession session,String parentId,String status){
	    User admin = (User)session.getAttribute("admin");
	    Map<String,Object> map = new HashMap<String, Object>();
	    map.put("parentId", parentId);
	    map.put("userId", admin.getUsername());
	    map.put("status", status);
	    List<Tree> list = this.queryMainMenuRecursive(map);
		return list;
	}
	
	/**
	 *  获取菜单管理父级菜单
	 */
	@ResponseBody
	@RequestMapping("queryParentMenu")
	public  List<Tree> queryParentMenu(){
	    Map<String,Object> map = new HashMap<String, Object>();
	    map.put("parentId", "0");
	    List<Tree> list = this.queryMainMenuRecursive(map);
	    List<Tree> resultList = new ArrayList<Tree>();
		Tree tree = new Tree();
		tree.setId("0");
		tree.setText("根目录");
		tree.setChildren(list);
		resultList.add(tree);
		return resultList;
	}
	
	/**
	 *  获取主页面用户菜单递归方法
	*/
	public List<Tree> queryMainMenuRecursive(Map<String,Object> map){
		List<Menu> list = menuService.queryMainMenu(map);
		List<Tree> treeList = new ArrayList<Tree>();
		Tree tree;
		for (Menu menu : list) {
			tree = new Tree();
			map.put("parentId",menu.getId());
			List<Tree> childList = queryMainMenuRecursive(map);
			if(childList!=null&&!childList.isEmpty()){
				tree.setChildren(childList);
				tree.setLeaf(false);
			}else{
				tree.setLeaf(true);
			}
			tree.setId(menu.getId().toString());
			tree.setText(menu.getName());
			tree.setUrl(menu.getUrl());
			treeList.add(tree);
		}
		return treeList;
	}
	
	@RequestMapping("updateMenuSortDown")
	@ResponseBody
	public Map<String,Object> updateMenuSortDown(String id){
		try {
			return menuService.updateMenuSortDown(id)>0?Constant.MSG.MAP_OK():Constant.MSG.MAP_ERR();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	@RequestMapping("updateMenuSortUp")
	@ResponseBody
	public Map<String,Object> updateMenuSortUp(String id){
		try {
			return menuService.updateMenuSortUp(id)>0?Constant.MSG.MAP_OK():Constant.MSG.MAP_ERR();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	
	
	/****
	 *  获取角色已分配菜单
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("qyeryMenuY")
	public List<Tree> qyeryMenuY(Integer roleId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("roleId",roleId);
		map.put("parentId","0");
		map.put("status",2);
		return queryMenuY(map);
		
	}
	public List<Tree> queryMenuY(Map<String,Object> map){
		List<Menu> list = menuService.queryMenuY(map);
		List<Tree> treeList = new ArrayList<Tree>();
		Tree tree;
		for (Menu menu : list) {
			tree = new Tree();
			map.put("parentId",menu.getId());
			List<Tree> childList = queryMenuY(map);
			if(childList!=null&&!childList.isEmpty()){
				tree.setChildren(childList);
				tree.setLeaf(false);
			}else{
				tree.setLeaf(true);
			}
			tree.setId(menu.getId().toString());
			tree.setText(menu.getName());
			treeList.add(tree);
		}
		return treeList;
	}
	
	/****
	 *  获取角色未分配菜单
	 * @param roleId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("qyeryMenuN")
	public List<Tree> qyeryMenuN(String roleId){
		return queryMenuN("0",roleId);
		
	}
	public List<Tree> queryMenuN(String menuParentid,String roleId){
		List<Tree> resultList = new ArrayList<Tree>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("roleId",roleId);
		map.put("parentId",menuParentid);
		map.put("status",2);
		List<Menu> list = menuService.queryMenuN(map);
		for(Menu menu:list){
			Tree tree = new Tree();
			if(menu.getChildCount()==0){
				tree.setId(menu.getId().toString());
				tree.setText(menu.getName());
				resultList.add(tree);
			}else{
				List<Tree> list1 = queryMenuN(menu.getId().toString(),roleId);
				if(list1.size()>0){
					tree.setId(menu.getId().toString());
					tree.setText(menu.getName());
					tree.setChildren(list1);
					resultList.add(tree);
				}
			}
		}
		return resultList;
	}
	
	/***
	 *  获取所有菜单
	 * 菜单管理页面
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllMenu")
	public List<Menu> getAllMenu(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("parentId","0");
		return getAllMenu(map);
	}
	public List<Menu> getAllMenu(Map<String,Object> map){
		List<Menu> list = menuService.queryMainMenu(map);
		List<Menu> resultList =new ArrayList<Menu>();
		for(Menu menu:list){
			map.put("parentId",menu.getId());
			List<Menu> childList = getAllMenu(map);
			if(childList!=null&&!childList.isEmpty()){
				menu.setChildren(childList);
			}
			resultList.add(menu);
		}
		return resultList;
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public Map<String,Object> delete(HttpSession session,Integer id){
		try {
			Menu menu = new Menu();
			menu.setId(id);
			menu.setUpdator(((User)session.getAttribute("admin")).getUsername());
			menuService.delete(menu);
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}
	
	@ResponseBody
	@RequestMapping("insert")
	public void insert(HttpServletResponse response,HttpSession session,Menu menu)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			menu.setCreator(((User)session.getAttribute("admin")).getUsername());
			menuService.insert(menu);
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_OK()));
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_ERR()));
		}
	}
	
	@ResponseBody
	@RequestMapping("update")
	public void update(HttpServletResponse response,HttpSession session,Menu menu)throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		try {
			menu.setUpdator(((User)session.getAttribute("admin")).getUsername());
			menuService.update(menu);
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_OK()));
		} catch (Exception e) {
			response.getWriter().print(JSON.toJSON(Constant.MSG.MAP_ERR()));
		}
	}
	
	@RequestMapping("queryMenuByParentId")
	@ResponseBody
	public List<Menu> queryMenuByParentId(int parentId){
		
		return menuService.queryMenuByParentId(parentId);
	}
	
	
	/***
	 * 获取前台菜单名称
	 * @param menuId
	 */
	@RequestMapping("queryParentMenuName")
	public void queryParentMenuName(HttpServletResponse response,int menuId){
		response.setContentType("text/html;charset=UTF-8"); 
		try {
			response.getWriter().print(menuService.queryParentMenuName(menuId));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * 获取前台侧边菜单
	 * @param response
	 * @param menuId
	 */
	@RequestMapping("queryViewLeftMenu")
	public void queryViewLeftMenu(HttpServletResponse response,int menuId){
		List<Menu> list = queryViewLeftMenu(menuId);
		response.setContentType("text/html;charset=UTF-8"); 
		try {
			response.getWriter().print(JSON.toJSONString(list,true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Menu> queryViewLeftMenu(int menuId){
		List<Menu> list = menuService.queryMenuByParentId(menuId);
		List<Menu> resultList =new ArrayList<Menu>();
		for(Menu menu:list){
			List<Menu> childList = queryViewLeftMenu(menu.getId());
			if(childList!=null&&!childList.isEmpty()){
				menu.setChildren(childList);
			}
			resultList.add(menu);
		}
		return resultList;
	}
	
	@RequestMapping("fileUpload")
	public void fileUpload(HttpServletRequest request,HttpServletResponse response,
			String fileObjectId,int MaxSize,String suffixStr,String savePath,
			int width,int height) throws IOException{
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        MultipartFile file  =  multipartRequest.getFile(fileObjectId);//获取文件流
        String fileName = file.getOriginalFilename();//获取文件全名
        String fileFirstName = fileName.substring(0,fileName.lastIndexOf("."));//获取文件名无扩展名
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());//获取文件扩展名
        String[] suffixs = suffixStr.split(",");
        boolean isSuffix = false;
        for(String str:suffixs){
        	if(str==suffix||str.equals(suffix)){
        		isSuffix=true;
        	}
        }
        String result = "";
		String msg = "";
		String path = "";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hhmmss");
		String resultName = fileFirstName+sdf.format(date)+"."+suffix;//新文件名
        if(isSuffix){
        	if(file.getSize()/1024<=MaxSize){
        		path = request.getSession().getServletContext().getRealPath("")+Constant.PATH.GETPATH(savePath); 
            	File targetFile = new File(path, resultName);  
                if(!targetFile.exists()){ //文件夹是否存在,自动新建
                    targetFile.mkdirs();  
                }  
                try {
                	//复制到服务器上
                    file.transferTo(targetFile);
                    if(width!=0&&height!=0){
                        BufferedImage bufferedImg = ImageIO.read(targetFile);
                        if(bufferedImg.getWidth()!=width||bufferedImg.getHeight()!=height){
                        	if(targetFile.delete()){
                        	}
                        	result = "false";
                        	msg = Constant.MSG.IMG_WH+width+"*"+height;
                        }else{
                        	result="true";
                        }
                    }else{
                    	result = "true";
                    }
                } catch (Exception e) {  
                	msg = Constant.MSG.UNKNOW_ERROR;
                }  	
        	}else{
        		msg = Constant.MSG.FILE_SIZE+MaxSize+"KB";
        		result = "false";
        	}
        }else{
        	msg = Constant.MSG.FILE_TYPE;
        	result = "false";
        }
        response.setContentType("text/html; charset=UTF-8");  
        PrintWriter out = response.getWriter();
        String res = "{ success:'"+result+"', msg:'"+msg+"',fileUrl:'"+resultName+"'}";
        out.print(res);
        out.flush();
	}
	
}
