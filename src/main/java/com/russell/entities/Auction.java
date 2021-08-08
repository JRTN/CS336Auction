package com.russell.entities;

import java.util.Date;

public class Auction {

    private int auctionId;
    private Book item;
    private User userCreated;
    private User userWinner;
    private double startPrice;
    private double reservePrice;
    private double currentPrice;
    private Date openDate;
    private Date closeDate;

    public Auction(int auctionId, Book item, User userCreated, double startPrice, double reservePrice,
                   Date openDate, Date closeDate) {
        this.auctionId = auctionId;
        this.item = item;
        this.userCreated = userCreated;
        this.userWinner = null;
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
        this.currentPrice = startPrice;
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public Book getItem() {
        return item;
    }

    public void setItem(Book item) {
        this.item = item;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public User getUserWinner() {
        return userWinner;
    }

    public void setUserWinner(User userWinner) {
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

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
