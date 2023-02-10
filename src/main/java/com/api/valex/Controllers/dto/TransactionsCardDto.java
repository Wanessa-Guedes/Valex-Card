package com.api.valex.Controllers.dto;

import java.util.List;

public class TransactionsCardDto {
    public float balance;
    public List<Object> transactions;

    public List<Object> recharges;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Object> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Object> transactions) {
        this.transactions = transactions;
    }

    public List<Object> getRecharges() {
        return recharges;
    }

    public void setRecharges(List<Object> recharges) {
        this.recharges = recharges;
    }
}
