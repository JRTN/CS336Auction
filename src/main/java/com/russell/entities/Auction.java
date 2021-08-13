package com.russell.entities;

import java.sql.Timestamp;
import java.util.Date;

public class Auction {

    private int auctionId;
    private String item;
    private String userCreated;
    private String userWinner;
    private double startPrice;
    private double reservePrice;
    private double currentPrice;
    private Timestamp openDate;
    private Timestamp closeDate;
    private int closed;

    public Auction(int auctionId, String item, String userCreated, String userWinner, double startPrice, double reservePrice, double currentPrice,
                   Timestamp openDate, Timestamp closeDate, int closed) {
        this.auctionId = auctionId;
        this.item = item;
        this.userCreated = userCreated;
        this.userWinner = userWinner;
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
        this.currentPrice = currentPrice;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.closed = closed;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserWinner() {
        return userWinner;
    }

    public void setUserWinner(String userWinner) {
        this.userWinner = userWinner;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Timestamp openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Timestamp closeDate) {
        this.closeDate = closeDate;
    }
}
