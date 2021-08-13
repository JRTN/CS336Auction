<%@ page import="com.russell.entities.Auction" %>
<%@ page import="com.russell.database.sqlcommands.AuctionTable" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.russell.web.WebError" %>
<%@ page import="com.russell.entities.Book" %>
<%@ page import="com.russell.database.sqlcommands.BookTable" %>
<%@ page import="com.russell.entities.Bid" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.russell.database.sqlcommands.BidTable" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.russell.entities.User" %><%--
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

    if (auction == null) {
        request.getRequestDispatcher("/notfound.jsp").forward(request, response);
        return;
    }

    Book book;

    try {
        book = BookTable.getByIsbn(auction.getItem());
    } catch (SQLException throwables) {
        WebError.errorPage(throwables, request, response);
        return;
    }

    if (book == null) {
        request.getRequestDispatcher("/notfound.jsp").forward(request, response);
        return;
    }

    ArrayList<Bid> bids;
    try {
        bids = BidTable.getByAuctionId(auction.getAuctionId());
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
        <a class="navbar-brand" href="myaccount.jsp">Auction</a>
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

<div class="container-fluid h100">
    <div class="card p-3">
        <div class="d-flex align-items-center">
            <div class="card-body">
                <h1 class="card-title"><%=book.getTitle() %><%=(auction.getClosed() == 0 ? "" : "(CLOSED)")%>
                </h1>
                <h2 class="card-text"><%=book.getAuthor() %>
                </h2>
                <h3 class="card-text">$<%=String.format("%.2f", auction.getCurrentPrice()) %>
                </h3>
                <h4 class="card-text">Auction Ends: <%=auction.getCloseDate() %>
                </h4>
                <h4 class="card-text">Seller: <%=auction.getUserCreated() %>
                </h4>
                <h4 class="card-text">Auction ID: <%=auction.getAuctionId() %>
                </h4>
                <p class="card-text">ISBN: <%=book.getIsbn() %>
                </p>
                <p class="card-text">Category: <%=book.getSubcategory().toString() %>
                </p>
                <p class="card-text">Genre: <%=book.getGenre() %>
                </p>
                <p class="card-text">Pages: <%=book.getPages() %>
                </p>
                <p class="card-text">Publisher: <%=book.getPublisher()%>
                </p>
                <p class="card-text">Publication Date: <%=book.getPublicationDate() %>
                </p>
            </div>
        </div>
    </div>

    <div class="card p-3">
        <div class="d-flex align-items-center">
            <div class="card-body">
                <h2 class="card-title">Bids</h2>
                <%
                    //Loop over bids
                    for (Bid bid : bids) {
                        String username = bid.getUserBidder();
                        double amount = bid.getAmount();
                        Date placedDate = bid.getPlacedDate();
                %>
                <p class="card-text">
                    <%=(auction.getClosed() == 1 && auction.getCurrentPrice() == bid.getAmount() && auction.getUserWinner().equals(username)) ? "(WINNER)" : ""%><%=username %> Amount: <%=String.format("$%.2f", amount)%> Placed: <%=placedDate %>
                </p>
                <%} %>
                <%
                    if (auction.getClosed() == 0 && currentUser != null && !currentUser.getUsername().equals(auction.getUserCreated())) {
                %>
                <a href="placebid.jsp?auctionid=<%=auctionId %>" class="btn btn-primary" role="button">Place Bid</a>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
