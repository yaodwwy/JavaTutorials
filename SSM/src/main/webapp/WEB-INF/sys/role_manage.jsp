<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/sys/role_manage.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',split:true" title="权限管理" style="width:220px;">
	<table id="datagrid"></table>
	</div>
	<div data-options="region:'center',title:'权限分配'">
	   <table cellpadding="0" cellspacing="0" width="100%" height="100%">
	   <tr>
	   <td width="40%" align="right" valign="middle">
	   <div class="easyui-panel" title="已分配菜单" style="width:300px;height:85%">
	   		<ul id="ytree" class="easyui-tree" data-options="method:'post',animate:true"></ul>
	   </div>
	   </td>
	   <td align="center" valign="middle">
               <table>
               <tr>
               <td>
               <a href="#" class="easyui-linkbutton" style="width:80px" onclick="fn_del_rolemenu()">》》</a>
               </td></tr>
			<tr>
               <td>
			<a href="#" class="easyui-linkbutton" style="width:80px" onclick="fn_add_rolemenu()">《《</a>
               </td></tr>
              </table>
          </td>
	   <td width="40%" align="left" valign="middle">
	   <div class="easyui-panel" title="未分配菜单" style="width:300px;height:85%">
	   		<ul id="ntree" class="easyui-tree" data-options="method:'post',animate:true"></ul>
	   </div>
	   </td>
	   </tr>
	   </table>
	</div>
	<div id="w" class="easyui-window" title="权限管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width:320px;height:200px;padding:10px;">
		<form id="ff" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>角色名称:</td>
	    			<td><input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" style="width:200px"></input><input type="hidden" id="id" name="id"></td>
	    		</tr>
	    		<tr>
	    			<td>角色描述:</td>
	    			<td><input class="easyui-textbox" type="text" id="desc" name="desc" data-options="required:true,multiline:true" style="width:200px;height:60px"></td>
	    		</tr>
	    		<tr>
	    			<td colspan="3" align="middle" style="padding-left:20px">
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_save()">保存</a>
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="$('#w').window('close');">取消</a>
	    			</td>
	    		</tr>
	    	</table>
	    </form>

	</div>
</body>
</html>