package com.russell.entities;

import java.util.Date;

public class BidAlert {

    private Auction auction;
    private Bid bid;
    private User forUser;
    private Date createdDate;

    public BidAlert(Auction auction, Bid bid, User forUser, Date createdDate) {
        this.auction = auction;
        this.bid = bid;
        this.forUser = forUser;
        this.createdDate = createdDate;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
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
