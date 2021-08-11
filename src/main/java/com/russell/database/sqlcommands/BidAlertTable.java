package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.BidAlertQueries;
import com.russell.entities.BidAlert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BidAlertTable {

    public static int createNewBidAlert(int auctionId, int bidId, String username, Date createdDate, boolean triggered) throws SQLException {
        String insertQuery = String.format(BidAlertQueries.INSERT_NEWBIDALERT,
                                            auctionId, bidId, username,
                                            ApplicationDAO.getDateTimeString(createdDate), triggered ? 1 : 0);

        return ApplicationDAO.runChangeQuery(insertQuery);
    }

    public static ArrayList<BidAlert> getFromResultSet(ResultSet set) throws SQLException {
        ArrayList<BidAlert> results = new ArrayList<>();
        while(set.next()) {
            int alertId = set.getInt("alert_id");
            int auctionId = set.getInt("auction_id");
            int bidId = set.getInt("bid_id");
            String username = set.getString("username");
            Date placedDate = set.getDate("placed_date");
            int triggered = set.getInt("triggered");

            BidAlert alert = new BidAlert(alertId, auctionId, bidId, username, placedDate, triggered == 1);
            results.add(alert);
        }

        return results;
    }
}
