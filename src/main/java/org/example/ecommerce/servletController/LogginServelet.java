package org.example.ecommerce.servletController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.connections.DatabaseConnections;
import org.example.ecommerce.dao.UserDao;
import org.example.ecommerce.models.User;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LogginServelet", value = "/LogginServelet")
public class LogginServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           // out.println("hitted the Loggin Servelet");
            String email =  request.getParameter("login-email");
            String password =  request.getParameter("login-password");

              // out.print(email+password);

            // Instantialting User Dao object
            try {
                UserDao userDao = new UserDao(DatabaseConnections.getConnection());
               User user =  userDao.login(email, password);

               if (user != null) {
                   out.print("Login Successful");
                   request.getSession().setAttribute("auth", user);
                   response.sendRedirect(request.getContextPath() + "/index.jsp");

               }else {
                   out.print("Login Failed");

               }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }
}