package org.example.ecommerce.servletController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.connections.DatabaseConnections;
import org.example.ecommerce.dao.OrderDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CanselOrdersServelet", value = "/cancel-order")
public class CanselOrdersServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
          String id = request.getParameter("id");
          if(id != null) {
              OrderDao orderDao = new OrderDao(DatabaseConnections.getConnection());
              orderDao.canselOrder(Integer.parseInt(id));
          }
          response.sendRedirect("orders.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}