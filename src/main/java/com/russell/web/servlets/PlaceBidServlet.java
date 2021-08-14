package com.russell.web.servlets;

import com.russell.database.sqlcommands.AutoBidTable;
import com.russell.database.sqlcommands.BidAlertTable;
import com.russell.database.sqlcommands.BidTable;
import com.russell.entities.Auction;
import com.russell.entities.Bid;
import com.russell.entities.User;
import com.russell.web.WebError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class PlaceBidServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Auction auction = (Auction)request.getSession().getAttribute("currentAuction");
        User user = (User)request.getSession().getAttribute("currentUser");

        if(user == null) {
            request.setAttribute("createResult", "You are not logged in.");
            request.getRequestDispatcher("/auctionpage.jsp?auctionid=" + auction.getAuctionId()).forward(request, response);
            return;
        }

        double bidAmount = Double.parseDouble(request.getParameter("bid_amount"));
        String chkAuto = request.getParameter("auto_increment");
        boolean autoBid = chkAuto != null;
        String chkAlert = request.getParameter("set_alert");
        boolean alert = chkAlert != null;

        if(bidAmount < auction.getCurrentPrice()) {
            request.setAttribute("createResult", String.format("You must bid an amount higher than $%.2f", auction.getCurrentPrice()));
            request.getRequestDispatcher("/placebid.jsp?auctionid=" + auction.getAuctionId()).forward(request, response);
            return;
        }

        //manual bid

        try {
            BidTable.createNewBid(auction.getAuctionId(), user.getUsername(), bidAmount, new Date());
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
            return;
        }

        if(autoBid) {
            int auctionId = auction.getAuctionId();
            String usernameBidder = user.getUsername();
            double currentBid = bidAmount;
            double upperLimit = Double.parseDouble(request.getParameter("bid_maximum"));
            double increment = Double.parseDouble(request.getParameter("bid_increment"));

            if(currentBid >= upperLimit) {
                request.setAttribute("createResult", "Automatic upper limit must be greater than the current bid.");
                request.getRequestDispatcher("/placebid.jsp?auctionid=" + auction.getAuctionId()).forward(request, response);
                return;
            }

            try {
                AutoBidTable.insertNewAutobid(auctionId, usernameBidder, currentBid, upperLimit, increment);
            } catch (SQLException throwables) {
                WebError.errorPage(throwables, request, response);
                return;
            }

        }

        if(alert) {
            Bid bid;
            try {
                bid = BidTable.runCustomQuery(String.format("SELECT * FROM bid WHERE auction_id = %d AND username_bidder = '%s' AND amount = %f;",
                        auction.getAuctionId(), user.getUsername(), bidAmount)).get(0);
            } catch (SQLException throwables) {
                WebError.errorPage(throwables, request, response);
                return;
            }

            try {
                BidAlertTable.createNewBidAlert(auction.getAuctionId(), bid.getBid_id(), bid.getUserBidder(), new Date(), false, false);
            } catch (SQLException throwables) {
                WebError.errorPage(throwables, request, response);
                return;
            }
        }

        request.getRequestDispatcher("/auctionpage.jsp?auctionid=" + auction.getAuctionId()).forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
