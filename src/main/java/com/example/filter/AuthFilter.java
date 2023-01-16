package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = "/user/*"
)
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse) response;
        if(null == session.getAttribute("user")){
            String username = (String)req.getAttribute("un");
            session.setAttribute("user",username);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }


    //write your code here!
}