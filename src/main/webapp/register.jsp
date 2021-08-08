<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 7/25/2021
  Time: 3:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
</head>
<body>
<div class="container" id="form_container">
    <form class="row text-center" action="RegisterServlet" method="post" style="width:340px">
        <h2 class="text-center">Register</h2>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Username" required="required" id="txt_username" name="username">
        </div>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Name" required="required" id="txt_name" name="name">
        </div>
        <div class="form-group">
            <input type="email" class="form-control" placeholder="Email" required="required" id="txt_email" name="email">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" required="required" id="txt_password" name="password">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Register</button>
        </div>
        <label style="color: #FF0000;" id="lbl_invalid">${createResult}</label>
    </form>
</div>
</body>
</html>
