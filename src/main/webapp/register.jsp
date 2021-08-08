<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container-fluid h-100">
    <div class="row justify-content-center align-items-center h-100">
        <div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
            <form class="row text-center" action="RegisterServlet" method="post" style="width:340px">
                <h1 class="text-center">Register</h1>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Username" required="required" id="txt_username"
                           name="username">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Name" required="required" id="txt_name"
                           name="name">
                </div>
                <div class="form-group">
                    <input type="email" class="form-control" placeholder="Email" required="required" id="txt_email"
                           name="email">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Password" required="required"
                           id="txt_password" name="password">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Register</button>
                </div>
                <label style="color: #FF0000;" id="lbl_invalid">${createResult}</label>
            </form>
        </div>
    </div>
</div>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
