<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2017/9/1
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<h3>用户登录</h3>
${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
<form name="f" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    <table>
        <tr>
            <td> User:</td>
            <td><input type="text" name="j_username"></td>
        </tr>
        <tr>
            <td> Password:</td>
            <td><input type="text" name="j_password"></td>
        </tr>
        <tr>
            <td colspan="2"><input name="submit" value="登录" type="submit"/></td>
        </tr>
        <tr>
            <td colspan="2"><input name="reset" value="重设" type="reset"/></td>
        </tr>

    </table>
</form>
</body>
</html>
