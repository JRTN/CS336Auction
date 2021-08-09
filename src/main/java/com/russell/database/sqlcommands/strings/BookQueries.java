package com.russell.database.sqlcommands.strings;

public interface BookQueries {

    /* INSERT STRINGS */
    String INSERT_NEWBOOK = "INSERT INTO book" +
                                "(isbn, title, subcategory, pages, author, publisher, genre, publication_date)" +
                            "VALUES" +
                                "('%s', '%s', '%s', %d, '%s', '%s', '%s', '%s');";

}
