package com.example.finance_tracker;

import java.time.LocalDateTime;

public class usertransaction {
    private String transactionId;
    private String dateTime;
    private userinfo user;
    private double amount;
    private String reason;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public userinfo getUser() {
        return user;
    }

    public void setUser(userinfo user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
