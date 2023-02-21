package com.api.valex.Controllers.dto;

import com.api.valex.Models.Payments;

public class PaymentCardDto {

    private long cardId;
    private String password;

    private Float amount;

    private long businessId;

    public PaymentCardDto(Payments payment) {
        this.cardId = payment.getCard().getId();
        this.amount = payment.getAmount();
        this.businessId = payment.getBusiness().getId();
    }

    public PaymentCardDto() {
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }
}
