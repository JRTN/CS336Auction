package com.russell.database.sqlcommands.strings;

public interface BidAlertQueries {

    String GET_BYUSERNAME = "SELECT * FROM bid_alert WHERE username = '%s';";

    String GET_BYAUCTIONID ="SELECT * FROM bid_alert WHERE auction_id = %d;";

    String INSERT_NEWBIDALERT = "INSERT INTO bid_alert " +
                                    "(auction_id, bid_id, username, created_date, triggered) " +
                                "VALUES " +
                                    "(%d, %d, '%s', '%s', %d);";
}
