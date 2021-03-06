package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.UserQueries;
import com.russell.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class UserTable {

    public static int CreateNewUser(String username, String password, String email, String name, String role) throws SQLException {
        String queryNames = String.format(UserQueries.SELECT_ALL_NAMES, username);
        ResultSet nameCheck = ApplicationDAO.runSelectQuery(queryNames);
        LinkedList<String> nameList = new LinkedList<>();

        while (nameCheck.next()) {
            nameList.add(nameCheck.getString("username"));
        }

        if (!nameList.isEmpty()) {
            return -1;
        }

        String queryInsert = String.format(UserQueries.INSERT_NEWUSER, username, password, email, name, role);

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

    public static User LoginUser(String username, String password) throws SQLException {
        User user;
        String query = String.format(UserQueries.SELECT_USERNAME_PASSWORD, username, password);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        ArrayList<User> results = getFromResultSet(resultSet);

        return results.isEmpty() ? null : results.get(0);
    }

    public static ArrayList<User> getFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<User> results = new ArrayList<>();

        while (resultSet.next()) {
            String username_res = resultSet.getString("username");
            String userpass_res = resultSet.getString("userpass");
            String useremail_res = resultSet.getString("useremail");
            String irlname_res = resultSet.getString("irlname");
            String userrole_res = resultSet.getString("userrole");

            User user = new User(username_res, userpass_res, useremail_res, irlname_res, userrole_res);
            results.add(user);
        }

        return results;
    }

}
