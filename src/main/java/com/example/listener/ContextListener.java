package com.example.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class ContextListener implements ServletContextListener {
    //write your code here!

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LocalDateTime rightNow = LocalDateTime.now();
        //add to ServletContext
        sce.getServletContext().setAttribute("servletTimeInit", rightNow);

    }
}
