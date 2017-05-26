<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登陆</title>
    <script type="text/javascript" src="<%=basePath %>jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/sys/login.js"></script>
</head>
<body style="margin:0; background:url(<%=basePath %>images/login/login_bg.gif) repeat-x">
<center>
<table id="__01" width="803" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="7" height="181" width="803" background="<%=basePath %>images/login/login_01.gif"></td>
	</tr>
	<tr>
		<td>
			<img src="<%=basePath %>images/login/login_02.gif" width="137" height="74" alt=""></td>
		<td colspan="5" style="background-image:url(<%=basePath %>images/login/login_03.gif);height:74px;width:523px;padding-left:20px" valign="middle">
			<table cellpadding="0" cellspacing="0">
			<tr>
			  <td valign="bottom"></td>
			  <td valign="bottom" style="font-family:'黑体';font-size:18px;color:#999999; padding-left:10px; font-weight:bold">后台管理系统</td><!-- 其才网 -->
			</tr>
			</table>
			
		</td>
		<td>
			<img src="<%=basePath %>images/login/login_04.gif" width="143" height="74" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="<%=basePath %>images/login/login_05.gif" width="137" height="147" alt=""></td>
		<td colspan="5" style="background-image:url(<%=basePath %>images/login/login_06.gif); height:147px;width:523px;" valign="middle" align="center">
			<table>
			  <form id="form_login" action="<%=basePath %>user/login" method="post">
				  <tr>
				     <td style="font-family:'黑体';font-size:16px;color:#999999; height:50px">用户名：</td><td><input id="username" name="username" type="text" style="border:solid #CCCCCC 1px;height:25px;width:180px; color:#666666"></td>
				  </tr>
				  <tr>
				     <td style="font-family:'黑体';font-size:16px;color:#999999; height:50px">密&nbsp;&nbsp;码：</td><td><input id="pwd" name="pwd" type="password" style="border:solid #CCCCCC 1px;height:25px;width:180px; color:#666666"></td>
				  </tr>
			  </form>
			</table>
		</td>
		<td>
			<img src="<%=basePath %>images/login/login_07.gif" width="143" height="147" alt=""></td>
	</tr>
	<tr>
		<td colspan="3">
			<img src="<%=basePath %>images/login/login_08.gif" width="314" height="55" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/login_09.gif" width="126" height="55" alt="" style="cursor:pointer" onclick="fn_login()"></td>
		<td colspan="2">
			<img src="<%=basePath %>images/login/login_10.gif" width="220" height="55" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/login_11.gif" width="143" height="55" alt=""></td>
	</tr>
	<tr>
		<td colspan="7">
			<img src="<%=basePath %>images/login/login_12.gif" width="803" height="54" alt=""></td>
	</tr>
	<tr>
		<td colspan="2">
			<img src="<%=basePath %>images/login/login_13.gif" width="224" height="80" alt=""></td>
		<td id="error" colspan="3" style="background:url(<%=basePath %>images/login/login_14.gif); width:389px; height:80px;color:#FF0000" align="center" valign="middle">
		  ${errorMsg}
		</td>
		<td colspan="2">
			<img src="<%=basePath %>images/login/login_15.gif" width="190" height="80" alt=""></td>
	</tr>
	<tr>
	<tr>
		<td colspan="7">
			<img src="<%=basePath %>images/login/login_16.gif" width="803" height="24" alt=""></td>
	</tr>
		<tr>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="137" height="1" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="87" height="1" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="90" height="1" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="126" height="1" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="173" height="1" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="47" height="1" alt=""></td>
		<td>
			<img src="<%=basePath %>images/login/fgf.gif" width="143" height="1" alt=""></td>
	</tr>
	<tr>
		<td colspan="7" style="color:#666666;font-family:'黑体';font-size:10px" align="center">
			Copyright© 2016-2020，All Rights Reserved<!-- 其才网  --> 
		</td>	
	</tr>
</table>
</center>
</body>
</html>