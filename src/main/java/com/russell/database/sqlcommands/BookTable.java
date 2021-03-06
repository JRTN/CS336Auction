package com.russell.database.sqlcommands;

import com.russell.database.ApplicationDAO;
import com.russell.database.sqlcommands.strings.BookQueries;
import com.russell.entities.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookTable {

    public static int createNewBook(String isbn, String title, String subcategory, int pages, String author,
                                    String publisher, String genre, Date publicationDate) throws SQLException {


        String queryInsert = String.format(BookQueries.INSERT_NEWBOOK, isbn, title, subcategory, pages, author,
                publisher, genre, ApplicationDAO.getDateString(publicationDate));

        return ApplicationDAO.runChangeQuery(queryInsert);
    }

    public static Book getByIsbn(String isbn) throws SQLException {
        String query = String.format(BookQueries.GET_BYISBN, isbn);
        ResultSet resultSet = ApplicationDAO.runSelectQuery(query);

        ArrayList<Book> results = getFromResultSet(resultSet);

        return results.isEmpty() ? null : results.get(0);
    }

    public static ArrayList<Book> getFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<Book> results = new ArrayList<>();

        while (resultSet.next()) {
            String isbn = resultSet.getString("isbn");
            String title = resultSet.getString("title");
            Book.SubCategory subcategory = Book.SubCategory.valueOf(resultSet.getString("subcategory"));
            int pages = resultSet.getInt("pages");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");
            String genre = resultSet.getString("genre");
            Date publicationDate = resultSet.getDate("publication_date");

            Book result = new Book(isbn, title, subcategory, pages, author, publisher, genre, publicationDate);
            results.add(result);
        }

        return results;
    }

}
