<%--
  Created by IntelliJ IDEA.
  User: licsber
  Date: 2020/5/14
  Time: 11:51 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

登陆失败！${param["username"]}，稍后自动跳转！

<%
    out.print("<script>" +
            "setTimeout(\"window.location='login.jsp'\",2000);" +
            "</script>");
%>

</body>
</html>
