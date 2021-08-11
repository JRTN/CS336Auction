<%@ page import="com.russell.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 7/26/2021
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("currentUser");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Auction</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <%
                        if (currentUser != null) {
                    %>
                    <a class="nav-link" href="myaccount.jsp">My Account</a>
                    <%
                    } else {
                    %>
                    <a class="nav-link" href="login.jsp">Login</a>
                    <%
                        }
                    %>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="createauction.jsp">Create Auction</a>
                </li>
            </ul>
            <form class="d-flex" action="auctionpage.jsp">
                <input class="form-control me-2" type="search" placeholder="Search Auction" aria-label="Search"
                       name="auctionid">
                <button class="btn btn-secondary" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
<h1>Internal Error</h1>
<label id="lbl_error">${errorBody}</label>
</body>
</html>
