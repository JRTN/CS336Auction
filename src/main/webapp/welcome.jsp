<%@ page import="com.russell.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 7/25/2021
  Time: 4:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h1>Welcome</h1>
    <%
        if(session.getAttribute("currentUser") == null) {
            out.println("Nobody is logged in.");
        } else {
            User user = (User)session.getAttribute("currentUser");
            String username = user.getUsername();
            String role = user.getUserrole();

            out.println("Welcome " + username);
            out.println("Your role is: " + role);
        }
    %>
</body>
</html>
