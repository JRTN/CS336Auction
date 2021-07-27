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
</head>
<body>
<h1>Register Page</h1>
<form action="RegisterServlet" method="post" id="form_register">
    <label>
        Username:
        <input type="text" placeholder="Enter Username" id="txt_username" name="username" required>
    </label>
    <label>
        Password:
        <input type="text" placeholder="Enter Password" id="txt_password" name="password" required>
    </label>
    <label>
        Email:
        <input type="text" placeholder="Enter Email" id="txt_email" name="email" required>
    </label>
    <label>
        Name:
        <input type="text" placeholder="Enter Name" id="txt_name" name="name" required>
    </label>
    <button type="submit">Register</button>
</form>
<label style="color: #FF0000;" id="lbl_invalid">${createResult}</label>
</body>
</html>
