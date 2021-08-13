package com.russell.database.sqlcommands.strings;

public interface AutoBidQueries {

    String INSERT_NEWAUTOBID = "INSERT INTO autobid " +
                                    "(auction_id, username_bidder, current_bid, upper_limit, increment) " +
                                "VALUES " +
                                    "(%d, '%s', %f, %f, %f);";

    String GET_BYUSER = "SELECT * FROM autobid WHERE username_bidder = '%s';";

    String GET_BYAUCTION = "SELECT * FROM autobid WHERE auction_id = %d;";
}
