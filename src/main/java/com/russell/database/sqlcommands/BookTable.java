package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.BookQueries;
import com.russell.entities.Book;

import java.sql.SQLException;
import java.util.Date;

public class BookTable {

    public static int createNewBook(String isbn, String title, String subcategory, int pages, String author,
                                     String publisher, String genre, Date publicationDate) throws SQLException {


        String queryInsert = String.format(BookQueries.INSERT_NEWBOOK, isbn, title, subcategory, pages, author,
                                            publisher, genre, ApplicationDAO.getDateString(publicationDate));

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

}
