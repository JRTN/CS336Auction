package com.russell.entities;

public class AutoBid {
    private int autobidId;
    private int auctionId;
    private String usernameBidder;
    private double currentBid;
    private double upperLimit;
    private double increment;

    public AutoBid(int autobidId, int auctionId, String usernameBidder, double currentBid, double upperLimit, double increment) {
        this.autobidId = autobidId;
        this.auctionId = auctionId;
        this.usernameBidder = usernameBidder;
        this.currentBid = currentBid;
        this.upperLimit = upperLimit;
        this.increment = increment;
    }

    public int getAutobidId() {
        return autobidId;
    }

    public void setAutobidId(int autobidId) {
        this.autobidId = autobidId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getUsernameBidder() {
        return usernameBidder;
    }

    public void setUsernameBidder(String usernameBidder) {
        this.usernameBidder = usernameBidder;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public double getIncrement() {
        return increment;
    }

    public void setIncrement(double increment) {
        this.increment = increment;
    }
}
