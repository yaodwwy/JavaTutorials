package com.adam.sys.userfunc.controller;

import com.adam.common.constant.Constant;
import com.adam.common.entity.Paging;
import com.adam.common.util.MD5Util;
import com.adam.common.util.SessionUtils;
import com.adam.sys.userfunc.entity.Menu;
import com.adam.sys.userfunc.entity.User;
import com.adam.sys.userfunc.entity.UserRole;
import com.adam.sys.userfunc.service.IMenuService;
import com.adam.sys.userfunc.service.IUserRoleService;
import com.adam.sys.userfunc.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名称：UserController
 */
@Controller
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController {

	@Resource
	private IUserService userService;
	@Resource
	private IMenuService menuService;
	@Resource
	private IUserRoleService userRoleService;

	@RequestMapping("tologin")
	public String tologin() {
		return Constant.PAGE.PLOGIN;
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		return Constant.PAGE.PLOGIN;
	}

	@RequestMapping("login")
	public String login(HttpServletRequest request, Model m,
			HttpSession session, String username, String pwd,
			String validateCode) throws Exception {
		User user = SessionUtils.getUser(request);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			if (username == null || "".equals(username)) {
				m.addAttribute("errorMsg", Constant.MSG.CODE_NULL);
				return Constant.PAGE.PLOGIN;
			}
			if (pwd == null || "".equals(pwd)) {
				m.addAttribute("errorMsg", Constant.MSG.PWD_NULL);
				return Constant.PAGE.PLOGIN;
			}
			map.put("username", username);
			map.put("pwd", MD5Util.encryption(pwd));
			user = userService.queryLogin(map);
			if (user == null) {
				m.addAttribute("errorMsg", Constant.MSG.ADMIN_NULL);
				return Constant.PAGE.PLOGIN;
			}
		}

		map.clear();
		map.put("userId", user.getUsername());
		map.put("parentId", "0");
		map.put("status",2);
		List<Menu> menuList = menuService.queryMainMenu(map);
		if (menuList == null || menuList.size() == 0) {
			m.addAttribute("errorMsg", Constant.MSG.ROLE_NULL);
			return Constant.PAGE.PLOGIN;
		}
		m.addAttribute("menuList", menuList);
		session.setAttribute("admin", user);
		return Constant.PAGE.PMAIN;
	}

	/**
	 * 
	 * updatepwd：(update password) (这里描述这个方法适用条件 – 可选) 创建人：Elvis 修改人：Elvis
	 * 
	 * @param session
	 * @param pwdold
	 * @param pwdnew
	 * @return Map<String,Object>
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("updatepwd")
	@ResponseBody
	public Map<String, Object> updatepwd(HttpSession session, String pwdold,
			String pwdnew) {
		User user = (User) session.getAttribute("admin");
		if (MD5Util.encryption(pwdold) == user.getPwd()
				|| MD5Util.encryption(pwdold).equals(user.getPwd())) {
			user.setPwd(MD5Util.encryption(pwdnew));
			userService.updatepwd(user);
			session.setAttribute("admin", user);
			return Constant.MSG.MAP_OK();
		} else {
			return Constant.MSG.MAP_OLDPWD();
		}
	}

	/**
	 * 
	 * delete：(逻辑删除用户) (这里描述这个方法适用条件 – 可选) 创建人：Elvis 修改人：Elvis
	 * 
	 * @param session
	 * @param id
	 * @return Map<String,Object>
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(HttpSession session, Integer id) {
		try {
			User user = new User();
			user.setUpdator(((User) session.getAttribute("admin"))
					.getUsername());
			user.setId(id);
			userService.delete(user);
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}
	}

	/**
	 * 
	 * update：(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选) 创建人：Elvis 修改人：Elvis
	 * 
	 * @param session
	 * @param username
	 * @param pwd
	 * @param fullname
	 * @param id
	 * @param roles
	 * @param status
	 * @return Map<String,Object>
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(HttpSession session, String username,
			String pwd, String fullname, String id, String roles, String status) {
		try {
			User admin = (User) session.getAttribute("admin");
			User record = new User();
			// record.setCreator(admin.getId());
			record.setFullname(fullname);
			// record.setPwd(MD5Util.encryption(pwd));
			record.setStatus(Integer.parseInt(status));
			record.setUpdator(admin.getUsername());
			record.setUsername(username);
			record.setId(Integer.parseInt(id));
			userService.update(record);
			userRoleService.delete(username);
			if (roles != null && !"".equals(roles)) {
				String[] roless = roles.split(",");
				List<UserRole> list = new ArrayList<UserRole>();
				UserRole userRole;
				for (String role : roless) {
					userRole = new UserRole();
					userRole.setCreator(admin.getUsername());
					userRole.setUserId(username);
					userRole.setRoleId(Integer.parseInt(role));
					list.add(userRole);
				}
				userRoleService.insert(list);
			}
			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Constant.MSG.MAP_ERR();
		}

	}

	/**
	 * 
	 * insert：(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选) 创建人：Elvis 修改人：Elvis
	 * 
	 * @param session
	 * @param username
	 * @param pwd
	 * @param fullname
	 * @param id
	 * @param roles
	 * @param status
	 * @return Map<String,Object>
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("insert")
	@ResponseBody
	public Map<String, Object> insert(HttpSession session, String username,
			String pwd, String fullname, String id, String roles, String status) {
		try {
			if (userService.queryCheckUserName(username) > 0) {
				return Constant.MSG.MAP_USNM();
			}
			User admin = (User) session.getAttribute("admin");
			User record = new User();
			record.setCreator(admin.getUsername());
			record.setFullname(fullname);
			record.setPwd(MD5Util.encryption(pwd));
			record.setStatus(Integer.parseInt(status));
			record.setUpdator(admin.getUsername());
			record.setUsername(username);
			userService.insert(record);
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("username", username);
			// map.put("pwd", MD5Util.encryption(pwd));
			// User user = userService.queryLogin(map);
			if (roles != null && !"".equals(roles)) {
				String[] roless = roles.split(",");
				List<UserRole> list = new ArrayList<UserRole>();
				UserRole userRole;
				for (String role : roless) {
					userRole = new UserRole();
					userRole.setCreator(admin.getUsername());
					userRole.setUserId(username);
					userRole.setRoleId(Integer.parseInt(role));
					list.add(userRole);
				}
				userRoleService.insert(list);
			}

			return Constant.MSG.MAP_OK();
		} catch (Exception e) {
			return Constant.MSG.MAP_ERR();
		}

	}

	@RequestMapping("queryAll")
	@ResponseBody
	public Paging<User> queryAll(int page, int rows) {
		Map condition = new HashMap();
		int start = rows * (page - 1);
		condition.put("start", start);
		condition.put("rows", rows);
		Paging<User> paging = userService.queryAll(condition);
		return paging;
	}

}
