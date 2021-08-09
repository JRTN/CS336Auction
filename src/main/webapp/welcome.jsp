<%@ page import="com.russell.entities.User" %>
<%@ page import="com.russell.entities.Auction" %>
<%@ page import="com.russell.database.sqlcommands.AuctionTable" %>
<%@ page import="com.russell.entities.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>
<%
    User user;
    if (session.getAttribute("currentUser") == null) {
        out.println("Nobody is logged in.");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        return;
    } else {
        user = (User) session.getAttribute("currentUser");
    }

    ArrayList<Auction> auctions = AuctionTable.getForUser(user);
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
                    <a class="nav-link active" href="welcome.jsp">My Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="createauction.jsp">Create Auction</a>
                </li>
            </ul>
            <form class="d-flex" action="auctionpage.jsp">
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
                <h2 class="card-title">My Account</h2>
                <p class="card-text">Username: <%=user.getUsername()%></p>
                <p class="card-text">Name: <%=user.getIrlname()%></p>
                <p class="card-text">Email: <%=user.getUseremail()%></p>
            </div>
        </div>
    </div>

    <div class="card p-3">
        <div class="d-flex align-items-center">
            <div class="card-body">
                <h2 class="card-title">My Auctions</h2>
                <%
                    for(Auction auction : auctions) {
                        int auctionId = auction.getAuctionId();
                        String isbn = auction.getItem();
                        double currentPrice = auction.getCurrentPrice();
                %>

                <p class="card-text">
                    ISBN: <a href="auctionpage.jsp?auctionid=<%=auctionId%>"><%=isbn%></a> Price: <%=currentPrice%>
                </p>
                <%} %>
                <a href="createauction.jsp" class="btn btn-primary" role="button">Create New Auction</a>
            </div>
        </div>
    </div>
</div>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
