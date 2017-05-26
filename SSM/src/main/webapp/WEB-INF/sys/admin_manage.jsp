<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>user manage</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/sys/admin_manage.js"></script>
</head>
<body>
	<table id="dg_user" class="easyui-datagrid" data-options="fit:true,rownumbers:true,singleSelect:true,url:'../user/queryAll',method:'POST',toolbar:toolbar,onDblClickRow:fn_update,pagination:true,pageSize:20">
		<thead>
			<tr>
				<th data-options="field:'id',width:80">ID</th>
				<th data-options="field:'username',width:100">工号</th>
				<th data-options="field:'fullname',width:240">姓名</th>
				
				<th data-options="field:'pwd',width:80,align:'center',hidden:true">密码</th>
				<th data-options="field:'status',formatter:fn_fmt_status,width:80,align:'center'">状态</th>
			</tr>
		</thead>
	</table>
	<div id="w" class="easyui-window" title="用户管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width:320px;height:410px">
	    <div class="easyui-panel" title="用户基本信息">
			<form id="ff" method="post">
		    	<table cellpadding="5">
		    		<tr>
		    			<td>登录名:</td>
		    			<td><input class="easyui-textbox" type="text" id="username" name="username" data-options="required:true" style="width:200px"></input><input type="hidden" id="id" name="id"></td>
		    		</tr>
		    		<tr id="tr_pwd">
		    			<td>登录密码:</td>
		    			<td><input class="easyui-textbox" type="text" id="pwd" name="pwd" data-options="required:true" style="width:200px"></input></td>
		    		</tr>
		    		<tr>
		    			<td>用户名:</td>
		    			<td><input class="easyui-textbox" type="text" id="fullname" name="fullname" data-options="required:true" style="width:200px"></input></td>
		    		</tr>
		    		
		    		<tr>
		    			<td>用户状态:</td>
		    			<td>
	                    <select class="easyui-combobox" data-options="width:80,panelHeight:'auto',required:true,editable:false" id="status" name="status">
	                    <option value="1">可用</option>
	                    <option value="0">禁用</option>
	                    </select>
	                    </td>
		    		</tr>
	
		    	</table>
		    </form>
	    </div>
	    <table id="dg_role" class="easyui-datagrid" title="用户角色分配" style="height:160px;width:100%" data-options="fitColumns:true,singleSelect:false,url:'../role/queryAll',method:'POST'">
			<thead>
				<tr>
					<th data-options="field:'name',width:120">角色名称</th>
					<th data-options="field:'desc',width:140">角色描述</th>
				</tr>
			</thead>
		</table>
	    <table width="100%"><tr><td align="center">
	    <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_save()">保存</a>
	    <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="$('#w').window('close');">取消</a>
	    </td></tr></table>
	    
	</div>
	
</body>
</html>