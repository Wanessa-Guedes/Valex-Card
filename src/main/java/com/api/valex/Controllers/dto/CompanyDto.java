package com.api.valex.Controllers.dto;

import com.api.valex.Models.Companies;
import com.api.valex.Models.Employees;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class CompanyDto {

    private long id;

    private String name;

    private String apiKey;

    public CompanyDto() {
    }

    public CompanyDto(Companies company) {
        this.id = company.getId();
        this.name = company.getName();
        this.apiKey = company.getApiKey();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
