<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>文章管理</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath %>ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=basePath %>js/sys/article_manage.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="border-bottom-width: 1px;">
		<div style="padding: 3px; border: 0px;" class="panel-header">
			<a id="add" href="javascript:void(0)" onclick="javascript:add();" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="upd" href="javascript:void(0)" onclick="javascript:upd();" class="easyui-linkbutton"
				data-options="iconCls:'icon-edit',plain:true,disabled:true">修改</a>
			<a id="del" href="javascript:void(0)" onclick="javascript:del();" class="easyui-linkbutton" 
				data-options="iconCls:'icon-remove',plain:true,disabled:true">删除</a>
			<a id="start" href="javascript:void(0)" onclick="javascript:change_status(0);" 
		class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true,disabled:true">显示</a>
			<a id="stop" href="javascript:void(0)" onclick="javascript:change_status(1);" 
		class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true,disabled:true">隐藏</a>
		</div>
		<div id="rolelayout_search" style="padding: 10px;padding-bottom:15px;">
			<table id="match">
				<tr>
				<td><label>标题：</label> <input class="easyui-textbox" id="stitle" name="title" /></td><td><label>
				&nbsp;&nbsp;专区：</label> <select  id="sPortion" name="sPortion" >
				            <option value="0">爱狗专区</option>
							<option value="1">爱猫专区</option>
							<option value="2">其它</option>
							<option value="3">SOS专区</option>
				           </select></td>
				<td><a id="search" href="javascript:void(0)" onclick="javascript:search1();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="datagrid" class="easyui-datagrid" rownumbers="true"
			data-options="fit:true,striped:true,singleSelect:true,url:'../article/querylist.do',method:'POST',
     		sortOrder:'desc',sortName:'id',remoteSort:false,
     		onDblClickRow:upd,onClickRow:choose,pagination:true,pageSize:20">
			<thead>
				<tr>
					<th data-options="field:'id',width:50,align:'center'" sortable="true">id</th>
					<th data-options="field:'pubTime',width:150,align:'center',formatter:fmt_time" sortable="true">创建时间</th>
					<th data-options="field:'title',width:250,align:'center'">标题</th>
					<th data-options="field:'portion',width:150,align:'center',formatter:fmt_type" sortable="true">类别</th>
					<th data-options="field:'userId',width:150,align:'center',formatter:getUserName" sortable="true">创建人</th>
					<th data-options="field:'comments',width:150,align:'center'" sortable="true">评论数</th>
					<th data-options="field:'status',width:150,align:'center',formatter:fmt_status"  sortable="true">状态</th>
				</tr>
			</thead>
		</table>
	</div>
	 <div id="mgrDiv" class="easyui-window" title="文章管理" data-options="modal:true,closed:true,iconCls:'icon-edit'"
		style="width: 1200px; height: 620px; padding: 10px;">
		<form id="mgrForm" method="post">
			<table cellpadding="5">
				<tr>
					<td style="text-align: right;">
						<span id="typeName">所在类别：</span>
					</td>
					<td>
						<select id="portion" name="portion">
							<option value="0">爱狗专区</option>
							<option value="1">爱猫专区</option>
							<option value="2">其它</option>
							<option value="3">SOS专区</option>
						</select>
						<input id="id" name="id" type="hidden">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="titleName">标题：</span>
					</td>
					<td><input class="easyui-textbox" id="title" name="title" data-options="required:true" style="width: 200px"></input></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="htmlContent">内容：</span>
					</td>
					<td>
						<textarea class="ckeditor" id="editor1" name="editor1"></textarea>
					</td>
				</tr>
			</table>

		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-save'" onclick="save()">保存</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'" onclick="cancel()" style="margin-left: 50px;">取消</a>
		</div>
		
		<div data-options="region:'center',border:false">
		<table id="commentDatagrid"></table>
		</div>
		
		
	</div> 

</body>
</html>