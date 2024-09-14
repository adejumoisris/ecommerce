<%--
  Created by IntelliJ IDEA.
  User: deca
  Date: 30/08/2024
  Time: 11:04 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignUP</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>


<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"> Pleas SignUP</div>
        <div class="card-body">
            <form action="SignUPServelet" method="post">
                <div class="form-group">
                    <label> Name</label>
                    <label>
                        <input type="text" class="form-control" name="name" placeholder="Enter your Name" required>
                    </label>
                </div>
                <div class="form-group">
                    <label> Email</label>
                    <label>
                        <input type="email" class="form-control" name="email" placeholder="Enter your Email" required>
                    </label>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <label>
                        <input type="password" class="form-control" name="password" placeholder="Enter your password" required>
                    </label>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"> SignUp </button>
                </div>

            </form>
        </div>

    </div>
</div>



<%@include file="includes/footer.jsp"%>

</body>
</html>
