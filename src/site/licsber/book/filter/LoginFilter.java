package site.licsber.book.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/bookAdd.html"},
        initParams = {@WebInitParam(name = "backurl", value = "login.jsp")
        })
public class LoginFilter implements Filter {
    String backUrl;

    public void init(FilterConfig fConfig) throws ServletException {
        if (fConfig.getInitParameter("backurl") != null) {
            backUrl = fConfig.getInitParameter("backurl");
        } else
            backUrl = "index.jsp";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        String flag = (String) session.getAttribute("isLogin");
        if (flag == null || !flag.equals("true")) {
            httpResponse.sendRedirect(backUrl);
        } else {
            chain.doFilter(request, response);
        }
    }
}
