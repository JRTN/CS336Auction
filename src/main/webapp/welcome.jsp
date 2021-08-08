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
                        Book book = auction.getItem();
                        String isbn = book.getIsbn();
                        String author = book.getAuthor();
                        double currentPrice = auction.getCurrentPrice();
                %>

                <p class="card-text">
                    ISBN: <a href="AuctionLoadServlet?auctionid=<%=auctionId%>"><%=isbn%></a> Author: <%=author%> Price: <%=currentPrice%>
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
