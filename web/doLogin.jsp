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
    <title>doLogin</title>
</head>
<body>

<%
    if (!request.getMethod().equals("POST")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    session.setAttribute("username", username);

    String userVCode = request.getParameter("userVCode");
    String validateCode = (String) session.getAttribute("validateCode");
    if (userVCode.equalsIgnoreCase(validateCode)) {
        if (username.equals("123") && password.equals("123")) {
            session.setAttribute("isLogin", "true");
            response.sendRedirect("success.jsp");
        } else {
            session.setAttribute("isLogin", "false");
            response.sendRedirect("error.jsp?username=" + username);
        }
    } else {
        out.println("<script>" +
                "alert('验证码输入错误！');" +
                "location='login.jsp';" +
                "</script>");
    }
%>

</body>
</html>
