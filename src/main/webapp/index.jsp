<%@ page import="com.russell.entities.Auction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.russell.database.sqlcommands.AuctionTable" %>
<%@ page import="com.russell.entities.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.russell.web.WebError" %>
<%@ page import="com.russell.entities.Book" %>
<%@ page import="com.russell.database.sqlcommands.BookTable" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 8/7/2021
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Auction Landing</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>

<%
    ArrayList<Auction> auctions;
    try {
        auctions = AuctionTable.getAllActions();
    } catch (SQLException throwables) {
        WebError.errorPage(throwables, request, response);
        return;
    }

%>

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
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
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

<div class="row" id="title">
    <h1 class="text-center">Book Auction</h1>
</div>

<%
    for (Auction auction : auctions) {
        Book book;
        try {
            book = BookTable.getByIsbn(auction.getItem());
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
            return;
        }
%>

<div class="card p-3">
    <div class="d-flex align-items-center">
        <div class="card-body">
            <a href="auctionpage.jsp?auctionid=<%=auction.getAuctionId()%>"><h2 class="card-title"><%=book.getTitle()%><%=(auction.getClosed() == 0 ? "" : "(CLOSED)")%>
            </h2></a>
            <p class="card-text">Price: $<%=String.format("%.2f", auction.getCurrentPrice())%>
            </p>
            <p class="card-text">Seller: <%=auction.getUserCreated()%>
            </p>
        </div>
    </div>
</div>

<%
    }
%>

<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
