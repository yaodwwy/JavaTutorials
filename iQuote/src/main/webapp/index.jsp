<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2017/9/1
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
welcome   <sec:authentication property="name"/>
<sec:authorize url="/admin.jsp">
<a href="admin.jsp">进入admin.jsp页面</a>
</sec:authorize>
</body>
</html>
