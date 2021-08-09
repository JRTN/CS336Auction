<%@ page import="com.russell.entities.Auction" %>
<%@ page import="com.russell.database.sqlcommands.AuctionTable" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.russell.web.WebError" %>
<%@ page import="com.russell.entities.Book" %>
<%@ page import="com.russell.database.sqlcommands.BookTable" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 8/8/2021
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auction</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>

<%

    int auctionId = Integer.parseInt(request.getParameter("auctionid"));
    Auction auction;
    try {
        auction = AuctionTable.getById(auctionId);
    } catch (SQLException throwables) {
        WebError.errorPage(throwables, request, response);
        return;
    }
    Book book;

    try {
        book = BookTable.getByIsbn(auction.getItem());
    } catch (SQLException throwables) {
        WebError.errorPage(throwables, request, response);
        return;
    }



%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">Auction</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="welcome.jsp">My Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="createauction.jsp">Create Auction</a>
                </li>
            </ul>
            <form class="d-flex" action="AuctionLoadServlet">
                <input class="form-control me-2" type="search" placeholder="Search Auction" aria-label="Search" name="auctionid">
                <button class="btn btn-secondary" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid h100">
    <div class="card p-3">
        <div class="d-flex align-items-center">
            <div class="card-body">
                <h1 class="card-title"><%=book.getTitle() %></h1>
                <h2 class="card-text"><%=book.getAuthor() %></h2>
                <h3 class="card-text">$<%=String.format("%.2f", auction.getCurrentPrice()) %></h3>
                <h4 class="card-text">Auction Ends: <%=auction.getCloseDate() %></h4>
                <h4 class="card-text">Seller: <%=auction.getUserCreated() %></h4>
                <h4 class="card-text">Auction ID: <%=auction.getAuctionId() %></h4>
                <p class="card-text">ISBN: <%=book.getIsbn() %></p>
                <p class="card-text">Category: <%=book.getSubcategory().toString() %></p>
                <p class="card-text">Genre: <%=book.getGenre() %></p>
                <p class="card-text">Pages: <%=book.getPages() %></p>
                <p class="card-text">Publisher: <%=book.getPublisher()%></p>
                <p class="card-text">Publication Date: <%=book.getPublicationDate() %></p>
            </div>
        </div>
    </div>

    <div class="card p-3">
        <div class="d-flex align-items-center">
            <div class="card-body">
                <h2 class="card-title">Bids</h2>
                <%
                    //Loop over bids
                %>

                <p class="card-text">
                    <% //Display Bids %>
                </p>
                <%//} %>
                <a href="placebid.jsp?auctionid=<% //auctionid %>" class="btn btn-primary" role="button">Place Bid</a>
            </div>
        </div>
    </div>
</div>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
