<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.setAttribute("uName", request.getParameter("uName"));
	session.setAttribute("uId", request.getParameter("uId"));
	/* session.setAttribute("tId", request.getParameter("tId")); */
	session.setAttribute("uHeadPic", request.getParameter("uHeadPic"));
	/* session.setAttribute("tName", request.getParameter("tName")); */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Language" content="zh-cn" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Chat</title>

<!-- <link rel="stylesheet" type="text/css" href="css/im-chat-style.css" /> -->
<style>
	input{
		margin-bottom: 4px;    border: 1px #222 solid;    background: transparent;    height: 18px;    width:80%;
	}
</style>
<script type="text/javascript" src="js/comet4j.js"></script>
<script type="text/javascript" src="js/talk.js"></script>
</head>
<body onload="init()" style="font-size: 0.5em">
	<div id="statebar" style="color: red; font-size: 1.8em;">工程模式</div>
	<div id="statebar" style="border-bottom: 1px solid; vertical-align: middle; line-height: 1.2em; font-size: 0.6em;">
		连接状态：<span id="workStyle"></span>；连接数量：<span id="connectorCount"></span>； 已用内存：<span id="usedMemory"></span>； <br> 可用内存： <span id="freeMemory"></span>； 内存容量：<span id="totalMemory"></span>； 最大容量：<span id="maxMemory"></span>； <br>系统已运行： <span id="startup"></span>
	</div>
	<div id="isOnline"></div>
	<div id="msgInfo"></div>
	<span id="imptId"></span>
	<ul id="logbox" style="width: 95%; overflow: auto; overflow-x: hidden; overflow-y: auto; word-break: break-all;">
	</ul>
	<div id="toolbar">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>tId：</td>
				<td width="100%"><input id="tId" value="" /></td>
			</tr>

			<tr>
				<td>tName：</td>
				<td><input id="tName" value="" /></td>
			</tr>
			<tr>
				<td>tHeadPic：</td>
				<td><input id="tHeadPic" value="" /></td>
			</tr>
			<tr>
				<td>uId：</td>
				<td><input id="uId" value="" /></td>
			</tr>
			<tr>
				<td>uName：</td>
				<td><input id="uName" value="匿名用户" /></td>
			</tr>
			<tr>
				<td>uHeadPic：</td>
				<td><input id="uHeadPic" value="" /></td>
			</tr>

			<tr>
				<td>info:</td>
				<td><input maxlength="200" placeholder="打个招呼...." id="info" onkeypress="return onSendBoxEnter(event);" type="text" /></td>
			</tr>
			<tr>
				<td colspan="2"><br>
						<button style="height: 60px; width: 80%;" onclick="send(info.value,tId.value);">发送</button></td>
			</tr>
		</table>
	</div>
</body>
</html>
