<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
    <h1>Login Page</h1>
    <form action="LoginServlet" method="post" id="form_login">
        <label>
            Username:
            <input type="text" placeholder="Enter Username" id="txt_username" name="username" required>
        </label>
        <label>
            Password:
            <input type="text" placeholder="Enter Password" id="txt_password" name="password" required>
        </label>
        <button type="submit">Login</button>
    </form>
    <label style="color: #FF0000;" id="lbl_invalid">${invalidLogin}</label>
</body>
</html>