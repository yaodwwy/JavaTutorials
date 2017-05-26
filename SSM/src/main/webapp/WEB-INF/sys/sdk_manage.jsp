<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>sdkversion manage</title>
<%@ include file="../common/common.jsp"%>
<script src="<%=basePath %>ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=basePath%>js/sys/sdk_manage.js"></script>
<script type="text/javascript" src="<%=basePath%>jquery-easyui/ajaxfileupload.js"></script>

</head>
<body class="easyui-layout" style="overflow:scroll;">
<div id="myPanel" style="padding: 15px;">
<div style="padding: 3px; border: 0px;" title="SDK发布设置" class="panel-header">
			<a id="expandAll" href="javascript:void(0)" onclick="displayType()" 
			class="easyui-linkbutton" data-options="iconCls:'icon-detail'">全部折叠</a>
			<a id="saveAll" href="javascript:void(0)" onclick="javascript:fn_appSave();" 
			class="easyui-linkbutton" data-options="iconCls:'icon-save'">全部保存</a>
		</div>
<form id="appForm" method="post">
			<table cellpadding="5">
				<tr>
					<td align="right">应用名称:</td>
					<td><input class="easyui-textbox" type="text" id="sdkName"
						name="sdkName" data-options="required:true" style="width: 200px"></input>
						<input type="hidden" id="id" name="id"></td>
				</tr>
				<tr id="">
					<td align="right">版本号:</td>
					<td><input class="easyui-textbox" type="text" id="version"
						name="version" data-options="required:true" style="width: 200px"></input></td>
				</tr>
				<tr>
					<td align="right">更新SDK:</td>
					<td>
					<a id="sdkDownload" class="easyui-linkbutton" data-options="iconCls:'icon-down'" href="#" 
					style="padding: 5px; color: blue;display:inline;">点击下载</a>
					
					 <input class="easyui-textbox" type="hidden" id="url" name="url" data-options="required:true" ></input>
					<input type="file" id="model_file" name="model_file" style="width:150px"/>
					<a id="uploadButton" class="easyui-linkbutton" href="javascript:void(0)">上传</a>
					<label style="color: gray;font-style: italic;">  仅能上传jar、rar、zip格式文件</label>
	    			<input type="hidden" id="pModelLocation" name="pModelLocation"/>
					</td>
				</tr>
				<!-- <tr>
					<td align="right">是否强制更新:</td>
					<td><select class="easyui-combobox"
						data-options="width:80,panelHeight:'auto',required:true,editable:false"
						id="isForce" name="isForce">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr> -->
			</table>
			<div id="ckEditers" class="easyui-panel" title="简介:" style="margin-bottom:30px; width: 98%;" 
			data-options="collapsible:true,left:150,top:150,tools:'#tt'" selected="true">
				<textarea class="ckeditor" id="sdkContent" name="sdkContent" data-options="required:true"></textarea> 
			</div>
			<div id="ckEditers" class="easyui-panel" title="使用说明:" style="margin-bottom:30px; width: 98%;" 
			data-options="collapsible:true,left:150,top:150,tools:'#tt'" selected="true">
					<textarea class="ckeditor" id="sdkinfo" name="sdkinfo" data-options="required:true"></textarea>
					</div>
			<div id="ckEditers" class="easyui-panel" title="调用示例:" style="margin-bottom:30px; width: 98%;" 
					data-options="collapsible:true,left:150,top:150,tools:'#tt'" selected="true">
					<textarea class="ckeditor" id="sdkinfoEx" name="sdkinfoEx" data-options="required:true"></textarea> 
					</div>
		</form>
</div>
</body>
</html>