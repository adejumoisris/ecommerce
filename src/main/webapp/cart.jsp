<%@ page import="org.example.ecommerce.connections.DatabaseConnections" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.example.ecommerce.models.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.ecommerce.models.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.ecommerce.dao.ProductDao" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth !=null){
        request.setAttribute("auth",auth);
    }

    // get the Sessions
    ArrayList<Cart> cart_List = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_List !=null){
        ProductDao pDao = new ProductDao(DatabaseConnections.getConnection());
        cartProduct =  pDao.getCartProducts(cart_List);
        double total = pDao.getTotalCartPrice(cart_List);
       request.setAttribute("cart-list", cart_List);
        request.setAttribute("total", total);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title> Cart Pages </title>
    <%@include file="includes/header.jsp"%>
    <style type="text/css">
        .table tbody td{
            vertical-align : middle;
        }
        .btn-incre, .btn-decre{
            box-shadow: none;
            font-size: 25px;
        }


    </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="d-flex py-3"><h3> Total Price :#${( total>0)?total:0} </h3> <a class="mx-3 btn btn-primary" href="CheckOutServelet"> Check Out</a> </div>
    <table class="table table-loght">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Buy Now</th>
            <th scope="col">Cancel</th>
        </tr>
        </thead>
        <tbody>
        <% if (cart_List !=null){
            for (Cart c : cartProduct){
                System.out.println(c.getQuantity());
                %>
        <tr>
            <td><%= c.getProductName()%></td>
            <td><%= c.getProductCategory()%></td>
            <td><%=c.getPrice()%></td>
            <td>
                <form action="/Order-now" method="post" class="form-inline">
                    <input type="hidden" name="id" value="<%=c.getId()%>" class="form-input">
                    <div class="form-group d-flex justify-content-between w-50">
                        <a class="btn btn-sm btn-incre" href="QuantyIntDecServelet?action=dec&id=<%=c.getId()%>"> <i class="fas fa-minus-square"></i> </a>
                        <input type="text" name="quantity" class="form-control w-50" value="<%=c.getQuantity()%>" readonly>
                        <a class="btn btn-sm btn-decre" href="QuantyIntDecServelet?action=inc&id=<%=c.getId()%>"> <i class="fas fa-plus-square"></i> </a>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm" >Buy</button>
                </form>
            </td>
            <td><a class="btn btn-sm btn-danger" href="removeFromCartServelet?id=<%=c.getId()%>">Remove</a> </td>
        </tr>


            <% }

        }
        %>

        </tbody>

    </table>

</div>


<%@include file="includes/footer.jsp"%>
</body>
</html>
