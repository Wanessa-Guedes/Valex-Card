package com.api.valex.Controllers.dto;

import com.api.valex.Models.TransactionType;

public class CreateCardDto {

    private Long employeeId;

    private TransactionType type;

    public CreateCardDto() {
    }

    public CreateCardDto(Long employeeId, TransactionType type) {
        this.employeeId = employeeId;
        this.type = type;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
