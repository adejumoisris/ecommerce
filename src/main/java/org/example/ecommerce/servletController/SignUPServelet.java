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
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "SignUPServelet", value = "/SignUPServelet")
public class SignUPServelet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao userDao;

   public void init(){
       try {
           Connection connection = DatabaseConnections.getConnection();
           userDao = new UserDao(connection);
       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
//        doGet(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        try {
            userDao.signUp(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("/index.jsp");

    }
}