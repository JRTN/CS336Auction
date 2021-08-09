package com.russell.web.servlets;

import com.russell.database.sqlcommands.AuctionTable;
import com.russell.database.sqlcommands.BookTable;
import com.russell.entities.Auction;
import com.russell.entities.User;
import com.russell.web.WebError;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAuctionServlet extends HttpServlet {

    public CreateAuctionServlet() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String isbn = request.getParameter("isbn").trim();
        String title = request.getParameter("title").trim();
        String subcategory = request.getParameter("subcategory").trim();
        String genre = request.getParameter("genre").trim();
        int pages = Integer.parseInt(request.getParameter("pages").trim());
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher").trim();

        SimpleDateFormat pubFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date publicationDate;
        try {
            publicationDate = pubFormat.parse(request.getParameter("publicationDate"));
        } catch (ParseException e) {
            WebError.errorPage(e, request, response);
            return;
        }

        double startPrice = Double.parseDouble(request.getParameter("startPrice"));
        double reservePrice = Double.parseDouble(request.getParameter("reservePrice"));

        SimpleDateFormat closeFormat = new SimpleDateFormat("yyyy-MM-dd'T'H:m");
        Date closeDate = null;
        try {
            closeDate = closeFormat.parse(request.getParameter("closeDate"));
        } catch (ParseException e) {
            WebError.errorPage(e, request, response);
        }

        User creatingUser = (User) request.getSession().getAttribute("currentUser");

        if(creatingUser == null) {
            //error
            return;
        }

        try {
            int bookResult = BookTable.createNewBook(isbn, title, subcategory, pages, author, publisher, genre, publicationDate);
            int auctionResult = AuctionTable.CreateNewAuction(isbn, creatingUser.getUsername(), startPrice, reservePrice, closeDate);

            if(bookResult != 1) {
                request.setAttribute("createResult", "Failed to create book. This book may already exist.");
                request.getRequestDispatcher("/createauction.jsp").forward(request, response);
                return;
            }
            if(auctionResult != 1) {
                request.setAttribute("createResult", "Failed to create auction.");
                request.getRequestDispatcher("/createauction.jsp").forward(request, response);
                return;
            }

            Auction created = AuctionTable.getByIsbn(isbn);

            request.getRequestDispatcher("/auctionpage.jsp?auctionid=" + created.getAuctionId()).forward(request, response);
        } catch (SQLException throwables) {
            WebError.errorPage(throwables, request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
