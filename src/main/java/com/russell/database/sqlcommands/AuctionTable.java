package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.AuctionQueries;
import com.russell.entities.Auction;
import com.russell.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AuctionTable {

    public static ArrayList<Auction> getAllActions() throws SQLException {
        ArrayList<Auction> auctions = new ArrayList<>();
        ResultSet resultSet = ApplicationDAO.runSelectQuery(AuctionQueries.GET_ALLAUCTIONS);

        while(resultSet.next()) {
            int auction_id = resultSet.getInt("auction_id");
            String item_isbn = resultSet.getString("item_isbn");
            String username_created = resultSet.getString("username_created");
            String username_won = resultSet.getString("username_won");
            double start_price = resultSet.getDouble("start_price");
            double reserve_price = resultSet.getDouble("reserve_price");
            double current_price = resultSet.getDouble("current_price");
            Date open_date = resultSet.getDate("open_date");
            Date close_date = resultSet.getDate("close_date");

            Auction auction = new Auction(auction_id, item_isbn, username_created, username_won, start_price, reserve_price, current_price, open_date, close_date);

            auctions.add(auction);
        }

        return auctions;
    }

    public static ArrayList<Auction> getForUser(User user) throws SQLException {
        ArrayList<Auction> auctions = new ArrayList<>();
        String query = String.format(AuctionQueries.GET_BYUSERCREATED, user.getUsername());
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        while(resultSet.next()) {
            int auction_id = resultSet.getInt("auction_id");
            String item_isbn = resultSet.getString("item_isbn");
            String username_created = resultSet.getString("username_created");
            String username_won = resultSet.getString("username_won");
            double start_price = resultSet.getDouble("start_price");
            double reserve_price = resultSet.getDouble("reserve_price");
            double current_price = resultSet.getDouble("current_price");
            Date open_date = resultSet.getDate("open_date");
            Date close_date = resultSet.getDate("close_date");

            Auction auction = new Auction(auction_id, item_isbn, username_created, username_won, start_price, reserve_price, current_price, open_date, close_date);

            auctions.add(auction);
        }

        return auctions;
    }

    public static int CreateNewAuction(String isbn, String username, double startPrice, double reservePrice, Date closeDate) throws SQLException {
        double currentPrice = startPrice;
        Date openDate = new Date();


        String queryInsert = String.format(AuctionQueries.INSERT_NEWAUCTION, isbn, username,
                                            startPrice, reservePrice, currentPrice, ApplicationDAO.getDateTimeString(openDate),
                                            ApplicationDAO.getDateTimeString(closeDate));

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

    public static Auction getByIsbn(String isbn) throws SQLException {
        String query = String.format(AuctionQueries.GET_BYISBN, isbn);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        Auction result = null;
        while(resultSet.next() && result == null) {
            int auction_id = resultSet.getInt("auction_id");
            String item_isbn = resultSet.getString("item_isbn");
            String username_created = resultSet.getString("username_created");
            String username_won = resultSet.getString("username_won");
            double start_price = resultSet.getDouble("start_price");
            double reserve_price = resultSet.getDouble("reserve_price");
            double current_price = resultSet.getDouble("current_price");
            Date open_date = resultSet.getDate("open_date");
            Date close_date = resultSet.getDate("close_date");

            result = new Auction(auction_id, item_isbn, username_created, username_won, start_price, reserve_price, current_price,
                                    open_date, close_date);
        }

        return result;
    }

    public static Auction getById(int auctionid) throws SQLException {
        String query = String.format(AuctionQueries.GET_BYID, auctionid);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        Auction result = null;
        while(resultSet.next() && result == null) {
            int auction_id = resultSet.getInt("auction_id");
            String item_isbn = resultSet.getString("item_isbn");
            String username_created = resultSet.getString("username_created");
            String username_won = resultSet.getString("username_won");
            double start_price = resultSet.getDouble("start_price");
            double reserve_price = resultSet.getDouble("reserve_price");
            double current_price = resultSet.getDouble("current_price");
            Date open_date = resultSet.getDate("open_date");
            Date close_date = resultSet.getDate("close_date");

            result = new Auction(auction_id, item_isbn, username_created, username_won, start_price, reserve_price, current_price,
                    open_date, close_date);
        }

        return result;
    }



}
