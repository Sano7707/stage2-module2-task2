package com.example.servlet;


import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final Users usersRepo = Users.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Object user = req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login.jsp");
        } else {
            resp.sendRedirect("/user/hello.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean rightLogin = usersRepo.getUsers().contains(login);
        boolean rightPassword = password != null && !password.trim().isEmpty();

        if (rightLogin && rightPassword) {
            req.getSession().setAttribute("user", login);
            resp.sendRedirect("/user/hello.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

}