package com.russell.database.sqlcommands.strings;

public interface BidQueries {
    String GET_BYAUCTIONID = "SELECT * FROM bid WHERE auction_id = '%d' ORDER BY placed_date ASC;";

    String GET_BYUSERNAME = "SELECT * FROM bid WHERE username_bidder = '%s' ORDER BY placed_date ASC;";

    String INSERT_NEWBID = "INSERT INTO bid " +
            "(auction_id, username_bidder, amount, placed_date) " +
            "VALUES " +
            "('%d', '%s', '%f', '%s';";
}
