package org.example.ecommerce.servletController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.connections.DatabaseConnections;
import org.example.ecommerce.dao.OrderDao;
import org.example.ecommerce.models.Cart;
import org.example.ecommerce.models.Order;
import org.example.ecommerce.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "CheckOutServelet", value = "/CheckOutServelet")
public class CheckOutServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            // retrieve all cart product
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            // user Authentication
            User auth = (User)request.getSession().getAttribute("auth");
            // check auth and cart list

            if (cart_list != null && auth != null) {
                for (Cart c: cart_list) {
                    // prepare the order object
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUid(auth.getId());
                    order.setQuantity(c.getQuantity());
                    order.setDate(sdf.format(date));
                    // instantiate the dao class
                    OrderDao oDao = new OrderDao(DatabaseConnections.getConnection());
                    // calling the insert methods
                    boolean result =oDao.insertOrder(order);
                    if (!result) break;
                }
                cart_list.clear();
                response.sendRedirect("orders.jsp");

            }else {
                if (auth == null) response.sendRedirect("login.jsp");
                response.sendRedirect("cart.jsp");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}