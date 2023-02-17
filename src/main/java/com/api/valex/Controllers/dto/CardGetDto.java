package com.api.valex.Controllers.dto;

import com.api.valex.Models.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class CardGetDto {
        private long id;
        private String number;

        private String cardHolderName;

        private String securityCode;
        private LocalDate expirationDate;

        private String password;

        private Boolean isVirtual;

        private Boolean isBlocked;

        private TransactionType type;

        private Employees employee;

        private List<Payments> payments;

        private List<Recharges> recharges;

    public CardGetDto() {
    }

    public CardGetDto(long id, String number, String cardHolderName, String securityCode, LocalDate expirationDate, String password, Boolean isVirtual, Boolean isBlocked, TransactionType type, Employees employee, List<Payments> payments, List<Recharges> recharges) {
        this.id = id;
        this.number = number;
        this.cardHolderName = cardHolderName;
        this.securityCode = securityCode;
        this.expirationDate = expirationDate;
        this.password = password;
        this.isVirtual = isVirtual;
        this.isBlocked = isBlocked;
        this.type = type;
        this.employee = employee;
        this.payments = payments;
        this.recharges = recharges;
    }

    public CardGetDto(Cards card) {
        this.id = card.getId();
        this.number = card.getNumber();
        this.cardHolderName = card.getCardHolderName();
        this.securityCode = card.getSecurityCode();
        this.expirationDate = card.getExpirationDate();
        this.password = card.getPassword();
        this.isVirtual = card.getVirtual();
        this.isBlocked = card.getBlocked();
        this.type = card.getType();
        this.employee = card.getEmployee();
        this.payments = card.getPayments();
        this.recharges = card.getRecharges();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getVirtual() {
        return isVirtual;
    }

    public void setVirtual(Boolean virtual) {
        isVirtual = virtual;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public List<Payments> getPayments() {
        return payments;
    }

    public void setPayments(List<Payments> payments) {
        this.payments = payments;
    }

    public List<Recharges> getRecharges() {
        return recharges;
    }

    public void setRecharges(List<Recharges> recharges) {
        this.recharges = recharges;
    }
}
