package com.russell.entities;

import java.util.Date;

public class StockAlert {
    private Book forBook;
    private User forUser;
    private Date createdDate;

    public StockAlert(Book forBook, User forUser, Date createdDate) {
        this.forBook = forBook;
        this.forUser = forUser;
        this.createdDate = createdDate;
    }

    public Book getForBook() {
        return forBook;
    }

    public void setForBook(Book forBook) {
        this.forBook = forBook;
    }

    public User getForUser() {
        return forUser;
    }

    public void setForUser(User forUser) {
        this.forUser = forUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
