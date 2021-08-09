package com.russell.entities;

import java.util.Date;

public class Bid {
    private int auction_id;
    private String userBidder;
    private double amount;
    private Date placedDate;

    public Bid(int auction_id, String userBidder, double amount, Date placedDate) {
        this.auction_id = auction_id;
        this.userBidder = userBidder;
        this.amount = amount;
        this.placedDate = placedDate;
    }

    public int getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(int auction_id) {
        this.auction_id = auction_id;
    }

    public String getUserBidder() {
        return userBidder;
    }

    public void setUserBidder(String userBidder) {
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
