<%@ page import="com.russell.entities.User" %>
<%@ page import="com.russell.entities.Auction" %>
<%@ page import="com.russell.database.sqlcommands.AuctionTable" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.russell.web.WebError" %><%--
  Created by IntelliJ IDEA.
  User: John
  Date: 8/8/2021
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place Bid</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    int auctionId = Integer.parseInt(request.getParameter("auctionid"));
    Auction auction;
    try {
        auction = AuctionTable.getById(auctionId);
    } catch (SQLException throwables) {
        WebError.errorPage(throwables, request, response);
        return;
    }

    if(auction == null) {
        request.getRequestDispatcher("/notfound.jsp").forward(request, response);
        return;
    }

    session.setAttribute("currentAuction", auction);
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
                    <a class="nav-link" href="welcome.jsp">My Account</a>
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

<div class="container-fluid h-100">
    <div class="row justify-content-center align-items-center h-100">
        <div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
            <form class="row text-center" action="PlaceBidServlet" method="post" style="width:340px">
                <h1 class="text-center">Place Bid [Auction <%=auctionId%>]</h1>
                <div class="form-group">
                    <input type="number" class="form-control" placeholder="Amount" required="required" id="num_amount"
                           name="bid_amount">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" placeholder="Increment(Optional)"
                           id="num_increment" name="bid_increment">
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" placeholder="Maximum(Optional)"
                           id="num_maximum" name="bid_maximum">
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="true" id="chk_auto_increment" name="auto_increment">
                    <label class="form-check-label" for="chk_auto_increment">
                        Auto bid?
                    </label>
                </div>
                <div class ="form-check">
                    <input class="form-check-input" type="checkbox" value="true" id="chk_set_alert" name="set_alert">
                    <label class="form-check-label" for="chk_set_alert">
                        Set alert?
                    </label>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Place</button>
                </div>
                <label style="color: #FF0000;" id="lbl_invalid">${createResult}</label>
            </form>
        </div>
    </div>
</div>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
