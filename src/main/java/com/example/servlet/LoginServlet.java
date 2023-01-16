package com.example.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    //write your code here!


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if(null == session.getAttribute("user")){
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        if((login.equals("admin") || login.equals("user")) && !password.equals("")){
            session.setAttribute("user","user");
            resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");

        }
        else {
            String nextJSP = "/login.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(req,resp);
        }
    }
}
