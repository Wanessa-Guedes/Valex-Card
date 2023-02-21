package com.api.valex.Controllers.dto;

import com.api.valex.Models.Recharges;

import java.sql.Timestamp;
import java.util.List;

public class RechargeDto {

    private Long id;

    private Timestamp timestamp;

    private float amount;

    public RechargeDto(Recharges recharge) {
            this.id = recharge.getId();
            this.timestamp = recharge.getTimestamp();
            this.amount = recharge.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
