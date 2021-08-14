package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.BidQueries;
import com.russell.entities.Bid;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class BidTable {

    public static Bid getByBidId(int bidId) throws SQLException {
        String query = String.format(BidQueries.GET_BYBIDID, bidId);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);
        ArrayList<Bid> bids = getFromResultSet(resultSet);

        return bids.isEmpty() ? null : bids.get(0);
    }

    public static ArrayList<Bid> getByAuctionId(int auctionId) throws SQLException {
        String query = String.format(BidQueries.GET_BYAUCTIONID, auctionId);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        return getFromResultSet(resultSet);
    }

    public static ArrayList<Bid> getByUsername(String username) throws SQLException {
        String query = String.format(BidQueries.GET_BYUSERNAME, username);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        return getFromResultSet(resultSet);
    }

    public static int createNewBid(int auctionId, String usernameBidder, double amount, Date placedDate) throws SQLException {
        String insertQuery = String.format(BidQueries.INSERT_NEWBID, auctionId, usernameBidder, amount, ApplicationDAO.getDateTimeString(placedDate));

        return ApplicationDAO.runChangeQuery(insertQuery);
    }

    public static ArrayList<Bid> runCustomQuery(String query) throws SQLException {
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        return getFromResultSet(resultSet);
    }

    public static ArrayList<Bid> getFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Bid> results = new ArrayList<>();

        while (resultSet.next()) {
            int bid_id = resultSet.getInt("bid_id");
            int auction_id = resultSet.getInt("auction_id");
            String username_bidder = resultSet.getString("username_bidder");
            double amount = resultSet.getDouble("amount");
            Timestamp placed_date = resultSet.getTimestamp("placed_date");

            Bid result = new Bid(bid_id, auction_id, username_bidder, amount, placed_date);

            results.add(result);
        }

        return results;
    }
}
