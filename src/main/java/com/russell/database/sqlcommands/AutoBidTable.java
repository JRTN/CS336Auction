package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.AutoBidQueries;
import com.russell.entities.AutoBid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutoBidTable {

    public static int insertNewAutobid(int auctionId, String usernameBidder, double currentBid,
                                       double upperLimit, double increment) throws SQLException {
        String queryInsert = String.format(AutoBidQueries.INSERT_NEWAUTOBID, auctionId, usernameBidder, currentBid,
                                            upperLimit, increment);

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

    public static AutoBid getByUser(String username) throws SQLException {
        String query = String.format(AutoBidQueries.GET_BYUSER, username);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);
        ArrayList<AutoBid> bids = getFromResultSet(resultSet);

        return bids.isEmpty() ? null : bids.get(0);
    }

    public static AutoBid getByAuction(int auctionId) throws SQLException {
        String query = String.format(AutoBidQueries.GET_BYAUCTION, auctionId);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);
        ArrayList<AutoBid> bids = getFromResultSet(resultSet);

        return bids.isEmpty() ? null : bids.get(0);
    }

    public static ArrayList<AutoBid> getFromResultSet(ResultSet set) throws SQLException {
        ArrayList<AutoBid> results = new ArrayList<>();

        while(set.next()) {
            int autobid_id = set.getInt("autobid_id");
            int auction_id = set.getInt("auction_id");
            String username_bidder = set.getString("username_bidder");
            double current_bid = set.getDouble("current_bid");
            double upper_limit = set.getDouble("upper_limit");
            double increment = set.getDouble("increment");

            AutoBid result = new AutoBid(autobid_id, auction_id, username_bidder, current_bid, upper_limit, increment);
            results.add(result);
        }

        return results;
    }
}
