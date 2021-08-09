package com.russell.database.sqlcommands.strings;

public interface UserQueries {
    /* SELECT STRINGS */
    String SELECT_USERNAME_PASSWORD = "SELECT * FROM users WHERE username = '%s' AND userpass = '%s';";
    String SELECT_ALL_NAMES = "SELECT username FROM users WHERE username = '%s';";

    /* INSERT STRINGS  */
    String INSERT_NEWUSER = "INSERT INTO users" +
            "(username, userpass, useremail, irlname, userrole)" +
            "VALUES" +
            "('%s', '%s', '%s', '%s', '%s');";

    /* UPDATE STRINGS */


    /* DELETE STRINGS */
}
