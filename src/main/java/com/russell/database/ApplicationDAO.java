package com.russell.database;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Class that interacts directly with database. Responsible for establishing a connection to the database
 * and executing queries against that connection.
 * <p>
 * Singleton pattern that wraps the connection from DriverManager.
 */
public class ApplicationDAO {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final String URL = "jdbc:mysql://localhost:3306/auction_db";

    private static final Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection temp;

        try {
            temp = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            temp = null;
        }

        connection = temp;
    }

    private ApplicationDAO() {
    }

    private static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static ResultSet runSelectQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();

        return stmt.executeQuery(query);
    }

    public static int runChangeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();

        return stmt.executeUpdate(query);
    }

    public static String getDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(date);
    }

    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(date);
    }

}
