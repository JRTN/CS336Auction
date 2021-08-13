package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.AuctionQueries;
import com.russell.entities.Auction;
import com.russell.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class AuctionTable {

    public static ArrayList<Auction> getAllActions() throws SQLException {
        ResultSet resultSet = ApplicationDAO.runSelectQuery(AuctionQueries.GET_ALLAUCTIONS);

        return getFromResultSet(resultSet);
    }

    public static ArrayList<Auction> getForUser(User user) throws SQLException {
        String query = String.format(AuctionQueries.GET_BYUSERCREATED, user.getUsername());
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        return getFromResultSet(resultSet);
    }

    public static int CreateNewAuction(String isbn, String username, double startPrice, double reservePrice, Date closeDate) throws SQLException {
        double currentPrice = startPrice;
        Date openDate = new Date();


        String queryInsert = String.format(AuctionQueries.INSERT_NEWAUCTION, isbn, username,
                startPrice, reservePrice, currentPrice, ApplicationDAO.getDateTimeString(openDate),
                ApplicationDAO.getDateTimeString(closeDate));

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

    public static int updateAuctionPrice(int auctionId, double amount) throws SQLException {
        String queryUpdate = String.format(AuctionQueries.UPDATE_CURRENTPRICE, amount, auctionId);

        return ApplicationDAO.runChangeQuery(queryUpdate);
    }

    public static Auction getByIsbn(String isbn) throws SQLException {
        String query = String.format(AuctionQueries.GET_BYISBN, isbn);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        ArrayList<Auction> results = getFromResultSet(resultSet);

        return results.isEmpty() ? null : results.get(0);
    }

    public static Auction getById(int auctionid) throws SQLException {
        String query = String.format(AuctionQueries.GET_BYID, auctionid);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        ArrayList<Auction> results = getFromResultSet(resultSet);

        return results.isEmpty() ? null : results.get(0);
    }

    public static ArrayList<Auction> getFromResultSet(ResultSet set) throws SQLException {
        ArrayList <Auction> results = new ArrayList<>();

        while (set.next()) {
            int auction_id = set.getInt("auction_id");
            String item_isbn = set.getString("item_isbn");
            String username_created = set.getString("username_created");
            String username_won = set.getString("username_won");
            double start_price = set.getDouble("start_price");
            double reserve_price = set.getDouble("reserve_price");
            double current_price = set.getDouble("current_price");
            Timestamp open_date = set.getTimestamp("open_date");
            Timestamp close_date = set.getTimestamp("close_date");
            int closed = set.getInt("closed");

            Auction result = new Auction(auction_id, item_isbn, username_created, username_won, start_price, reserve_price, current_price,
                    open_date, close_date, closed);
            results.add(result);
        }

        return results;
    }


}
