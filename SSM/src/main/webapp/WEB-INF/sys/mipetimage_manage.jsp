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
<title>首页管理</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath %>ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=basePath %>js/sys/mipetimage_manage.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui/ajaxfileupload.js"></script>

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
				<td><label>标题：</label> <input class="easyui-textbox" id="stitle" name="title" /></td>
				<td><a id="search" href="javascript:void(0)" onclick="javascript:search1();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="datagrid" class="easyui-datagrid"
			data-options="fit:true,striped:true,singleSelect:true,url:'../mipetimage/querylist.do',method:'POST',onDblClickRow:upd,onClickRow:choose,
			pagination:true,pageSize:20">
			<thead>
				<tr>
					<th data-options="field:'imageUrl',width:180,align:'center',formatter:fmt_image">图片</th>
					<th data-options="field:'imageLink',width:150,align:'center'">文章ID</th>
					<th data-options="field:'status',width:60,align:'center',formatter:fmt_status">状态</th>
				</tr>
			</thead>
		</table>
	</div>
	 <div id="mgrDiv" class="easyui-window" title="首页管理" data-options="modal:true,closed:true,iconCls:'icon-edit'"
		style="width: 370px; height: 360px; padding: 5px;">
		<form id="mgrForm" method="post">
			<table cellpadding="5">
				<tr>
					<td style="text-align: right;">
						<span>图片：</span>
					</td>
					<td>
					<img alt="图片" src="" name="imageShow" id="imageShow" height="120px"/>
	    			<input type="hidden" id="imageUrl" name="imageUrl"/><br>
	    			<span style="color: gray; line-height:28px;">图片尺寸必须为:960*640px 仅支持jpg、gif文件</span><br>
	    			<input type="file" id="model_file" name="model_file" style="width:200px"/>
					<a id="uploadButton" class="easyui-linkbutton" href="javascript:void(0)">上传</a>
	    			<input type="hidden" id="pModelLocation" name="pModelLocation"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span>文章ID：</span>
					</td>
					<td>
					<input class="easyui-textbox" id="imageLink" name="imageLink" data-options="required:true" style="width: 200px"></input>
					<input id="id" name="id" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span>状态：</span>
					</td>
					<td>
					<select id="status" name="status">
							<option value="0">启用</option>
							<option value="1">禁用</option>
						</select>
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
		
		
	</div> 

</body>
</html>