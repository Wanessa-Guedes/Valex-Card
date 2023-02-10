package com.api.valex.Controllers.dto;

public class ReechargeCardDto {

    public long cardId;
    public long employeeId;

    public float amout;

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public float getAmout() {
        return amout;
    }

    public void setAmout(float amout) {
        this.amout = amout;
    }
}
