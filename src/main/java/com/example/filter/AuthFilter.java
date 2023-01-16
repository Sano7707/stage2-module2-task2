package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = "/user/*"
)
public class AuthFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        HttpServletResponse resp = (HttpServletResponse) response;
        if(null == session.getAttribute("user")){
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
    //write your code here!
}