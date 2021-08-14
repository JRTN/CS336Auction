package com.russell.entities;

import java.sql.Timestamp;
import java.util.Date;

public class Bid {
    private int bid_id;
    private int auction_id;
    private String userBidder;
    private double amount;
    private Timestamp placedDate;

    public Bid(int bid_id, int auction_id, String userBidder, double amount, Timestamp placedDate) {
        this.bid_id = bid_id;
        this.auction_id = auction_id;
        this.userBidder = userBidder;
        this.amount = amount;
        this.placedDate = placedDate;
    }

    public int getBid_id() {
        return bid_id;
    }

    public void setBid_id(int bid_id) {
        this.bid_id = bid_id;
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

    public void setPlacedDate(Timestamp placedDate) {
        this.placedDate = placedDate;
    }
}
