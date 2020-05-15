<%--
  Created by IntelliJ IDEA.
  User: licsber
  Date: 2020/5/14
  Time: 10:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login</title>
    <link type="text/css" href="css/login.css" rel="stylesheet">
    <script>
        function change() {
            document.getElementById("validate").src = "ValidateServlet?random=" + Math.random();
        }
    </script>
</head>

<body>

<div id="login">
    <h1>用户登录</h1>
    <hr>
    <form action="doLogin.jsp" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><label>
                    <input type="text" name="username">
                </label></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><label>
                    <input type="password" name="password"/>
                </label></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td>
                    <label>
                        <input type="text" name="userVCode" class="textVcode"/>
                    </label>
                    <span>
                 <img src="ValidateServlet" id="validate" onclick="change()" alt="code"/>
                 <a href="javascript:change()">看不清，换一张</a>
                </span>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">登录</button>
                    <button type="reset">重置</button>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>

</html>