<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid h-100">
    <div class="row justify-content-center align-items-center h-100">
        <div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
            <form class="row text-center" action="LoginServlet" method="post" style="width:340px">
                <h1 class="text-center">Log in</h1>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" required="required" id="txt_username"
                           name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Password" required="required"
                           id="txt_password" name="password">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Login</button>
                </div>
                <label style="color: #FF0000;" id="lbl_invalid">${invalidLogin}</label>
            </form>
        </div>
    </div>
</div>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>