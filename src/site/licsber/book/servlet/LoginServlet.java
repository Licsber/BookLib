package site.licsber.book.servlet;

import site.licsber.book.db.UserDB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("username", username);

        String userVCode = req.getParameter("userVCode");
        String validateCode = (String) session.getAttribute("validateCode");
        if (userVCode.equalsIgnoreCase(validateCode)) {
            if (UserDB.isLogin(username, password)) {
                session.setAttribute("isLogin", "true");
                resp.sendRedirect("success.jsp?username=" + username);
            } else {
                session.setAttribute("isLogin", "false");
                resp.sendRedirect("error.jsp?username=" + username);
            }
        } else {
            resp.getWriter().println("<script>" +
                    "alert('验证码输入错误！');" +
                    "location='login.jsp';" +
                    "</script>");
        }
    }
}
