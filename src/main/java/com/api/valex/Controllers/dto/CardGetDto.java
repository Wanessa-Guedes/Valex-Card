package com.api.valex.Controllers.dto;

import com.api.valex.Models.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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

        private EmployeeDto employee;

        private List<PaymentCardDto> payments;

        private List<RechargeDto> recharges;

    public CardGetDto() {
    }

    public CardGetDto(long id, String number, String cardHolderName, String securityCode, LocalDate expirationDate, String password, Boolean isVirtual, Boolean isBlocked, TransactionType type, EmployeeDto employee, List<PaymentCardDto> payments, List<RechargeDto> recharges) {
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
        List<RechargeDto> recharg = new ArrayList<>();
        card.getRecharges().forEach(obj -> recharg.add(new RechargeDto(obj)));
        List<PaymentCardDto> payment = new ArrayList<>();
        card.getPayments().forEach(obj -> payment.add(new PaymentCardDto(obj)));
        this.id = card.getId();
        this.number = card.getNumber();
        this.cardHolderName = card.getCardHolderName();
        this.securityCode = card.getSecurityCode();
        this.expirationDate = card.getExpirationDate();
        this.password = card.getPassword();
        this.isVirtual = card.getVirtual();
        this.isBlocked = card.getBlocked();
        this.type = card.getType();
        this.employee = new EmployeeDto(card.getEmployee());
        this.payments = payment;
        this.recharges = recharg;
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

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public List<PaymentCardDto> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentCardDto> payments) {
        this.payments = payments;
    }

    public List<RechargeDto> getRecharges() {
        return recharges;
    }

    public void setRecharges(List<RechargeDto> recharges) {
        this.recharges = recharges;
    }
}
