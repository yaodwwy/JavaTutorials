<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>菜单管理</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/sys/menu_manage.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui/ajaxfileupload.js"></script>

</head>
<body class="easyui-layout">
	<table id="menu_tg" class="easyui-treegrid" style="width:100%;height:100%" data-options="
				url: '<%=basePath %>menu/getAllMenu',
				method: 'post',
				idField: 'id',
				treeField: 'name',toolbar:toolbar,onDblClickRow:fn_upd">
		<thead>
			<tr>
				<th data-options="field:'id'" width="25" align="left">id</th>
				<th data-options="field:'name'" width="165" align="left">菜单名称</th>
				<th data-options="field:'url'"  width="290" align="left">后台路径</th>
			</tr>
		</thead>
	</table>
	<div id="w" class="easyui-window" title="菜单管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" 
	style="width:330px;height:320px;padding:10px;">
		<form id="ff" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>菜单名称:</td>
	    			<td>
	    			<input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" style="width:200px"></input>
	    			<input type="hidden" id="id" name="id">
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>上级菜单:</td>
	    			<td><input id="parentId" name="parentId" data-options="required:true,method: 'post',onChange:fn_parentid_change"/></td>
	    		</tr>
	    		<tr>
	    			<td>后台地址:</td>
	    			<td><input class="easyui-textbox" type="text" id="url" name="url" style="width:200px" ></input></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="middle" style="padding-left:20px">
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-save'" class="easyui-linkbutton" onclick="fn_save()">保存</a>
	    			  <a href="javascript:void(0)" data-options="iconCls:'icon-cancel'" class="easyui-linkbutton" onclick="$('#w').window('close');">取消</a>
	    			</td>
	    		</tr>
	    	</table>
	    </form>

	</div>
	
</body>
</html>