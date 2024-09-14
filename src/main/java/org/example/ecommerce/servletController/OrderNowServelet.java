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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "/Order-now", value = "/Order-now")
public class OrderNowServelet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try(PrintWriter out = response.getWriter()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date date = new Date();

            User auth = (User)request.getSession().getAttribute("auth");
            if(auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <=0){
                    productQuantity = 1;
                }
                Order newOrder = new Order();
                newOrder.setId(Integer.parseInt(productId));
                newOrder.setUid(auth.getId());
                newOrder.setQuantity(productQuantity);
                newOrder.setDate(sdf.format(date));
                OrderDao orderDao = new OrderDao(DatabaseConnections.getConnection());
               boolean result =  orderDao.insertOrder(newOrder);

               if(result) {
                   ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                   if(cart_list != null) {
                       for(Cart c : cart_list) {
                           if(c.getId()==Integer.parseInt(productId)) {
                               cart_list.remove(cart_list.indexOf(c));
                               break;
                           }
                       }
                   }
                   response.sendRedirect("orders.jsp");

               }else {
                   out.print("Order Failed");

               }


            }else {
                response.sendRedirect("login.jsp");
            }

        }catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);

    }
}