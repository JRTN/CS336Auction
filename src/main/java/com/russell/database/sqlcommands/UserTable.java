package com.russell.database.sqlcommands;

import com.russell.entities.User;
import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.UserQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserTable {

    public static int CreateNewUser(String username, String password, String email, String name, String role) throws SQLException {
        String queryNames = String.format(UserQueries.SELECT_ALL_NAMES, username);
        ResultSet nameCheck = ApplicationDAO.runSelectQuery(queryNames);
        LinkedList<String> nameList = new LinkedList<>();

        while(nameCheck.next()) {
            nameList.add(nameCheck.getString("username"));
        }

        if(!nameList.isEmpty()) {
            return -1;
        }

        String queryInsert = String.format(UserQueries.INSERT_NEWUSER, username, password, email, name, role);

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

    public static User LoginUser(String username, String password) throws SQLException {
        User user;
        String query = String.format(UserQueries.SELECT_USERNAME_PASSWORD, username, password);
        ResultSet result = ApplicationDAO.runSelectQuery(query);
        LinkedList<User> userList = new LinkedList<>();

        while (result.next()) {
            String username_res = result.getString("username");
            String userpass_res = result.getString("userpass");
            String useremail_res = result.getString("useremail");
            String irlname_res = result.getString("irlname");
            String userrole_res = result.getString("userrole");

            user = new User(username_res, userpass_res, useremail_res, irlname_res, userrole_res);
            userList.add(user);
        }

        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

}
