<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>商品管理</title>
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>js/sys/goods_manage.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="border-bottom-width: 1px;">
		<div style="padding: 3px; border: 0px;" class="panel-header">
			<a id="add" href="javascript:void(0)" onclick="javascript:add();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a> <a id="upd" href="javascript:void(0)" onclick="javascript:upd();" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true,disabled:true">修改</a> <a id="del" href="javascript:void(0)" onclick="javascript:del();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true,disabled:true">删除</a> <a id="start" href="javascript:void(0)" onclick="javascript:change_status(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true,disabled:true">显示</a> <a id="stop" href="javascript:void(0)" onclick="javascript:change_status(1);" class="easyui-linkbutton"
				data-options="iconCls:'icon-tip',plain:true,disabled:true">隐藏</a>
		</div>
		<div id="rolelayout_search" style="padding: 10px; padding-bottom: 15px;">
			<table id="match">
				<tr>
					<td><label>标题：</label> <input class="easyui-textbox" id="stitle" name="title" /></td>
						<td><label>&nbsp;&nbsp;城市：</label><input class="easyui-combobox" id="scity" name="scity"  style="width: 150px"  
						data-options="editable:false,url:'../dictdetail/queryList.do?code=city',	method:'get',valueField:'description',textField:'description',panelHeight:'auto'"/>
						</td>
						<td><label>&nbsp;&nbsp;所在类别：</label> <select id="stype" name="stype">
								<option value="0">淘宝</option>
								<option value="1">微商</option>
						</select> </td>
					<td><a id="search" href="javascript:void(0)" onclick="javascript:search1();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="datagrid" class="easyui-datagrid" data-options="fit:true,striped:true,singleSelect:true,url:'../goods/querylist.do',method:'POST',onDblClickRow:upd,onClickRow:choose,remoteSort:false,
			pagination:true,pageSize:20">
			<thead>
				<tr>
					<th data-options="field:'id',width:50,align:'center'">id</th>
					<th data-options="field:'types',width:80,align:'center',formatter:fmt_type" sortable="true">分类</th>
					<th data-options="field:'title',width:250,align:'center'" sortable="true">标题</th>
					<th data-options="field:'postPrice',width:80,align:'center',formatter:fmt_price" sortable="true">邮费</th>
					<th data-options="field:'goodsPrice',width:80,align:'center',formatter:fmt_price" sortable="true">价格</th>
					<th data-options="field:'position',width:110,align:'center'" sortable="true">所在城市</th>
					<th data-options="field:'mainImage',width:150,align:'center',formatter:fmt_mainImage">主图</th>
					<th data-options="field:'goodsLink',width:150,align:'center',formatter:fmt_link">链接</th>
					<th data-options="field:'enabled',width:60,align:'center',formatter:fmt_status" sortable="true">状态</th>
					<th data-options="field:'updateTime',width:150,align:'center',formatter:fmt_time" sortable="true">更新时间</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="mgrDiv" class="easyui-window" title="商品管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width: 1200px; height: 620px; padding: 10px;">
		<form id="mgrForm" method="post">

			<table cellpadding="10">
				<tbody>
					<tr>
						<td><span style="text-align: right;">标题：</span></td>
						<td colspan="3"><input class="easyui-textbox" id="title" name="title" data-options="required:true" style="width: 380px" /></td>
					</tr>
					<tr>
						<td><span style="text-align: right;">所在类别：</span></td>
						<td><select id="types" name="types">
								<option value="0">淘宝</option>
								<option value="1">微商</option>
						</select> <input id="id" name="id" type="hidden" /></td>
						<td colspan="2" rowspan="5"><img alt="主图" src="" name="imageShow" id="imageShow" width="20%" /></td>
					</tr>
					<tr>
						<td><span style="text-align: right;">邮费：</span></td>
						<td><input class="easyui-textbox" id="postPrice" name="postPrice" style="width: 80px"></td>
					</tr>
					<tr>
						<td><span style="text-align: right;">价格：</span></td>
						<td><input class="easyui-textbox" id="goodsPrice" name="goodsPrice" data-options="required:true" style="width: 80px"></td>
					</tr>
					<tr>
						<td><span style="text-align: right;">所在城市：</span></td>
						<td><input class="easyui-combobox" id="city" name="city"  style="width: 80px"  
						data-options="required:true,editable:false,url:'../dictdetail/queryList.do?code=city',	method:'get',valueField:'description',textField:'description',panelHeight:'auto'"/>
						</td>
					</tr>
					<tr>
						<td><span style="text-align: right;">链接：</span></td>
						<td><input class="easyui-textbox" id="goodsLink" name="goodsLink" data-options="required:true" style="width: 200px"></td>
					</tr>
					<tr>
						<td><span style="text-align: right;">状态：</span></td>
						<td><select id="enabled" name="enabled">
								<option value="0">启用</option>
								<option value="1">禁用</option>
						</select></td>
						<td colspan="2">主图:   <input class="easyui-textbox" type="hidden" id="mainImage" name="mainImage" /> <input type="file" id="model_file" name="model_file" style="width: 200px" /> <a id="uploadButton" class="easyui-linkbutton" href="javascript:void(0)">上传</a> <input type="hidden" id="pModelLocation" name="pModelLocation" /></td>
					</tr>
					<tr>
						<td colspan="1"><span style="text-align: right;">内容：</span></td>
						<td colspan="3"><textarea class="ckeditor" id="editor1" name="editor1"></textarea></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save()">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="cancel()" style="margin-left: 50px;">取消</a>
		</div>


	</div>

</body>
</html>