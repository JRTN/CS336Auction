package com.russell.database.sqlcommands.strings;

public interface AuctionQueries {

    String INSERT_NEWAUCTION = "INSERT INTO auction" +
            "(item_isbn, username_created, start_price, reserve_price, current_price, open_date, close_date, closed)" +
            "VALUES" +
            "('%s', '%s', '%f', '%f', '%f', '%s', '%s', %d);";

    String UPDATE_CURRENTPRICE = "UPDATE auction SET current_price = %f WHERE auction_id = %d;";

    String GET_BYISBN = "SELECT * FROM auction " +
            "WHERE item_isbn = '%s';";

    String GET_BYID = "SELECT * FROM auction WHERE auction_id = '%d';";

    String GET_BYUSERCREATED = "SELECT * FROM auction " +
            "WHERE username_created = '%s';";

    String GET_BYWINNER = "SELECT * FROM auction WHERE username_won = '%s';";

    String GET_ALLAUCTIONS = "SELECT * FROM auction ORDER BY close_date DESC;";
}
