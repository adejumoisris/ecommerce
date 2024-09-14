<%@ page import="org.example.ecommerce.models.User" %>
<%@ page import="org.example.ecommerce.models.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth !=null){
//        request.setAttribute("auth",auth);
        response.sendRedirect("index.jsp");
    }

    ArrayList<Cart> cart_List = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_List !=null){
        request.setAttribute("cart-list", cart_List);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title> Login</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
<%@include file="includes/navbar.jsp"%>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"> Please Login</div>
        <div class="card-body">
            <form action="LogginServelet" method="post">
                <div class="form-group">
                    <label> Email</label>
                    <label>
                        <input type="email" class="form-control" name="login-email" placeholder="Enter your Email" required>
                    </label>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <label>
                        <input type="password" class="form-control" name="login-password" placeholder="Enter your password" required>
                    </label>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"> Login </button>
                </div>

            </form>
        </div>

    </div>
</div>

<%@include file="includes/footer.jsp"%>
</body>
</html>
