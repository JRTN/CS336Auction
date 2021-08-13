package com.russell.database.sqlcommands.strings;

public interface BidQueries {
    String GET_BYBIDID = "SELECT * FROM bid WHERE bid_id = '%d';";

    String GET_BYAUCTIONID = "SELECT * FROM bid WHERE auction_id = '%d' ORDER BY placed_date DESC;";

    String GET_BYUSERNAME = "SELECT * FROM bid WHERE username_bidder = '%s' ORDER BY placed_date DESC;";

    String INSERT_NEWBID = "INSERT INTO bid " +
            "(auction_id, username_bidder, amount, placed_date) " +
            "VALUES " +
            "('%d', '%s', '%f', '%s');";
}
