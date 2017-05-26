<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path1 + "/";
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath1 %>jquery-easyui/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath1 %>jquery-easyui/themes/default/easyui.css" id="easyuiTheme">
<link rel="stylesheet" type="text/css" href="<%=basePath1 %>jquery-easyui/themes/icon.css">
<script type="text/javascript" src="<%=basePath1 %>jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath1 %>jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<style>
	*{font-size:12px;}
	body {font-family:helvetica,tahoma,verdana,sans-serif;padding:0px;font-size:13px;margin:0;}
</style>