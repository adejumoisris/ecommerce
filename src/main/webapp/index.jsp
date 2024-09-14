<%@ page import="org.example.ecommerce.connections.DatabaseConnections" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.example.ecommerce.models.User" %>
<%@ page import="org.example.ecommerce.dao.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.ecommerce.models.Products" %>
<%@ page import="org.example.ecommerce.models.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }
    // calling instants of the product methods
    List<Products> products;
    try {
        ProductDao prd = new ProductDao(DatabaseConnections.getConnection());
        products = prd.getAllProducts();
    } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
    }
    // get the Sessions
    ArrayList<Cart> cart_List = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_List !=null){
        request.setAttribute("cart-list", cart_List);
    }


%>
<!DOCTYPE html>
<html>
<head>
    <title> Welcome to Surulere Market , Place to get your Electronics</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>


<div class="container">
    <div class="card header my-3"> All Products </div>
    <div class="row">
<%--         Looping through those Products --%>
    <%
        if (!products.isEmpty()) {
            for (Products p: products){
                System.out.println(p.getProductCategory());
    %>
    <div class="col-md-4 my-3">
        <div class="card W-500" style="width: 18rem;">
            <img class="card-img-top" src="productsImages/<%=p.getImage()%>" alt="Product Image">
            <div class="card-body">
                <h5 class="card-title"> <%=p.getProductName() %></h5>

                <h6 class="price"> <%= p.getPrice() %></h6>
                <h6 class="category"> <%= p.getProductCategory()%></h6>
               <div class="mt-2 d-flex justify-content-between">
                   <a href="AddToCartServelet?id=<%= p.getId()%>" class="btn btn-dark"> Add to cart</a>
                   <a href="Order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary">Buy</a>

               </div>

            </div>
        </div>
    </div>


           <% }
        }
    %>

    </div>
</div>


<%@include file="includes/footer.jsp"%>
</body>
</html>