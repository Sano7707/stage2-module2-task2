package com.example.servlet;


import com.example.Users;

import javax.servlet.RequestDispatcher;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(null == session.getAttribute("user")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user/hello.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String password = req.getParameter("password");
        String login = req.getParameter("login");
        if((login.equals("admin") || login.equals("user")) && !password.equals("")){
            String username = (String)req.getAttribute("un");
            session.setAttribute("user",username);
            resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");

        }
        else {
            String nextJSP = "/login.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(req,resp);
        }
    }
}
