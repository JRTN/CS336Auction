package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.BidQueries;
import com.russell.entities.Bid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BidTable {

    public static ArrayList<Bid> getByAuctionId(int auctionId) throws SQLException {
        String query = String.format(BidQueries.GET_BYAUCTIONID, auctionId);

        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        ArrayList<Bid> bids = new ArrayList<>();

        while (resultSet.next()) {
            int auction_id = resultSet.getInt("auction_id");
            String username_bidder = resultSet.getString("username_bidder");
            double amount = resultSet.getDouble("amount");
            Date placed_date = resultSet.getDate("placed_date");

            Bid result = new Bid(auction_id, username_bidder, amount, placed_date);

            bids.add(result);
        }

        return bids;
    }

    public static ArrayList<Bid> getByUsername(String username) throws SQLException {
        String query = String.format(BidQueries.GET_BYUSERNAME, username);

        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        ArrayList<Bid> bids = new ArrayList<>();

        while (resultSet.next()) {
            int auction_id = resultSet.getInt("auction_id");
            String username_bidder = resultSet.getString("username_bidder");
            double amount = resultSet.getDouble("amount");
            Date placed_date = resultSet.getDate("placed_date");

            Bid result = new Bid(auction_id, username_bidder, amount, placed_date);

            bids.add(result);
        }

        return bids;
    }

    public static int createNewBid(int auctionId, String usernameBidder, double amount, Date placedDate) throws SQLException {
        String insertQuery = String.format(BidQueries.INSERT_NEWBID, auctionId, usernameBidder, amount, ApplicationDAO.getDateTimeString(placedDate));

        return ApplicationDAO.runChangeQuery(insertQuery);
    }
}
