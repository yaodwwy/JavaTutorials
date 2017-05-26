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
<script type="text/javascript" src="<%=basePath %>js/sys/foster_manage.js"></script>
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
				<td><label>店铺名称：</label> <input class="easyui-textbox" id="stitle" name="title" /></td><td><label>&nbsp;&nbsp;服务项目：</label>
					 <input id="sservice" style="width: 150px" class="easyui-combobox" data-options="editable:false,
					url:'../dictdetail/queryList.do?code=services',
					method:'get',valueField:'typeName',textField:'description',panelHeight:'auto'"/>
					</td>
					<td><label>&nbsp;&nbsp;城市：</label><input class="easyui-combobox" id="scity" name="city"  style="width: 150px"  
						data-options="editable:false,url:'../dictdetail/queryList.do?code=city',method:'get',valueField:'description',textField:'description',panelHeight:'auto'"/>
						</td>
				<td><a id="search" href="javascript:void(0)" onclick="javascript:search1();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="datagrid" class="easyui-datagrid"
			data-options="fit:true,striped:true,singleSelect:true,url:'../fos/querylist.do',method:'POST',onDblClickRow:upd,onClickRow:choose,
			pagination:true,remoteSort:false,pageSize:20">
			<thead>
				<tr>
					<th data-options="field:'name',width:150,align:'center'" sortable="true">店铺名称</th>
					<th data-options="field:'maxPic',width:150,align:'center',formatter:fmt_mainImage">图片</th>
					<th data-options="field:'items',width:150,align:'center'" sortable="true">服务项目</th>
					<th data-options="field:'phone',width:150,align:'center'" sortable="true">联系电话</th>
					<th data-options="field:'address',width:150,align:'center'" sortable="true">地址</th>
					<th data-options="field:'opening',width:150,align:'center'" sortable="true">营业时间</th>
					<th data-options="field:'serviceInfo',width:150,align:'center'" sortable="true">服务项目</th>
					<th data-options="field:'status',width:150,align:'center',formatter:fmt_status" sortable="true">状态</th>
					<th data-options="field:'updateTime',width:150,align:'center',formatter:fmt_time" sortable="true">最后修改时间</th>
				</tr>
			</thead>
		</table>
	</div>
	 <div id="mgrDiv" class="easyui-window" title="寄养管理" data-options="modal:true,closed:true,iconCls:'icon-edit'" style="width: 390px; height: 570px; padding: 5px;">
		<form id="mgrForm" method="post"><table cellpadding="5">
				<tr>
					<td style="text-align: right;">
						<span id="typeName">店铺名称：</span>
					</td>
					<td><input class="easyui-textbox" id="name" name="name" data-options="required:true" style="width: 200px"/>
						<input type="hidden" id="id" name="id"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span>图片：</span>
					</td>
					<td>
					<img alt="图片" src="" name="max" id="max"/>
	    			<input type="hidden" id="maxPic" name="maxPic"/><br>
	    			<span style="color:gray; line-height:28px;">图片尺寸必须为:960*520px 仅支持jpg、gif文件</span><br>
	    			<input type="file" id="model_file" name="model_file" style="width:150px"/>
					<a id="uploadMax" class="easyui-linkbutton" href="javascript:void(0)">上传</a>
	    			<input type="hidden" id="pModelLocation" name="pModelLocation"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="titleName">服务项目：</span>
					</td>
					<td>
					 <input id="services" style="width: 200px" class="easyui-combobox" data-options="required:true,editable:false,
					url:'../dictdetail/queryList.do?code=services',
					method:'get',valueField:'typeName',textField:'description',multiple:true,panelHeight:'auto'"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="titleName">联系电话：</span>
					</td>
					<td><input type="text"  class="easyui-numberbox"  id="phone" name="phone" data-options="required:true,validType:'length[8,13]'"  style="width: 200px"/></td>
				</tr>
					<tr>
					<td style="text-align: right;">
						城市：
					</td>
					<td><input class="easyui-combobox" id="city" name="city"  style="width: 200px"  
						data-options="required:true,editable:false,url:'../dictdetail/queryList.do?code=city',	method:'get',valueField:'description',textField:'description',panelHeight:'auto'"/>
						</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="">地址：</span>
					</td>
					<td><input class="easyui-textbox" id="address" name="address"  style="width: 200px"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="">营业时间：</span>
					</td>
					<td><input class="easyui-combobox" id="openning" name="openning" style="width: 200px" 
						data-options="required:true,editable:false,url:'../dictdetail/queryList.do?code=openning',	method:'post',valueField:'typeName',textField:'typeName',panelHeight:'auto'"/></td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<span id="htmlContent">服务介绍：</span>
					</td>
					<td>
						<textarea class="" cols="29" rows="6" id="serviceInfo" name="serviceInfo"></textarea>
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
		
		<div data-options="region:'center',border:false">
		<table id="commentDatagrid"></table>
		</div>
		
		
	</div> 

</body>
</html>