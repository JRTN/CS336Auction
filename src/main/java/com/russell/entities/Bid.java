package com.russell.entities;

import java.util.Date;

public class Bid {
    private Auction auction;
    private User userBidder;
    private double amount;
    private Date placedDate;

    public Bid(Auction auction, User userBidder, double amount, Date placedDate) {
        this.auction = auction;
        this.userBidder = userBidder;
        this.amount = amount;
        this.placedDate = placedDate;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUserBidder() {
        return userBidder;
    }

    public void setUserBidder(User userBidder) {
        this.userBidder = userBidder;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(Date placedDate) {
        this.placedDate = placedDate;
    }
}
