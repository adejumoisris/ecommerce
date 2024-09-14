<%@ page import="org.example.ecommerce.connections.DatabaseConnections" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.example.ecommerce.models.User" %>
<%@ page import="org.example.ecommerce.models.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.example.ecommerce.dao.OrderDao" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.ecommerce.models.Order" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%

    DecimalFormat dcf = new DecimalFormat("0.00");
    request.setAttribute("dcf", dcf);
    User auth = (User) request.getSession().getAttribute("auth");
    List<Order> orders = null;
    if (auth !=null){
        request.setAttribute("auth",auth);
        orders = new OrderDao(DatabaseConnections.getConnection()).userOrders(auth.getId());
    }else {
        response.sendRedirect("login.jsp");
    }

    ArrayList<Cart> cart_List = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_List !=null){
        request.setAttribute("cart-list", cart_List);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Orders</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="card-header my-3">All Orders</div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Cansel</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (orders !=null){
                for (Order o : orders) {%>
        <tr>


        <td><%=o.getDate()%></td>
        <td><%=o.getProductName()%></td>
        <td><%=o.getProductCategory()%></td>
        <td><%=o.getQuantity()%></td>
        <td><%= dcf.format(o.getPrice()) %></td>
        <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel</a></td>

        </tr>

                <%}
            }else
        %>

        </tbody>

    </table>

</div>


<%@include file="includes/footer.jsp"%>
</body>
</html>
