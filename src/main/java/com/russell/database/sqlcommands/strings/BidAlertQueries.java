package com.russell.database.sqlcommands.strings;

public interface BidAlertQueries {

    String GET_BYUSERNAME = "SELECT * FROM bid_alert WHERE username = '%s';";

    String GET_TRIGGERED_BYUSERNAME = "SELECT * FROM bid_alert WHERE username = '%s' AND triggered = 1;";

    String GET_BYAUCTIONID ="SELECT * FROM bid_alert WHERE auction_id = %d;";

    String INSERT_NEWBIDALERT = "INSERT INTO bid_alert " +
                                    "(auction_id, bid_id, username, created_date, triggered, acknowledged) " +
                                "VALUES " +
                                    "(%d, %d, '%s', '%s', %d, %d);";

    String ACKNOWLEDGE_ALERT = "UPDATE bid_alert SET triggered = 0, acknowledged = 1 WHERE alert_id = %d;";
}
