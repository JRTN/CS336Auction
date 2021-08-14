package com.russell.entities;

import java.sql.Timestamp;
import java.util.Date;

public class BidAlert {
    private int alertId;
    private int auctionId;
    private int bidId;
    private String username;
    private Timestamp createdDate;
    private boolean triggered;
    private boolean acknowledged;

    public BidAlert(int alertId, int auctionId, int bidId, String username,
                    Timestamp createdDate, boolean triggered, boolean acknowledged) {
        this.alertId = alertId;
        this.auctionId = auctionId;
        this.bidId = bidId;
        this.username = username;
        this.createdDate = createdDate;
        this.triggered = triggered;
        this.acknowledged = acknowledged;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public int getAlertId() {
        return alertId;
    }

    public void setAlertId(int alertId) {
        this.alertId = alertId;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
}
