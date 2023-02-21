package com.api.valex.Controllers.dto;

import com.api.valex.Models.Companies;
import com.api.valex.Models.Employees;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeDto {

    private long id;

    private String fullname;

    private String cpf;

    private String email;

    private CompanyDto company;

    public EmployeeDto(Employees employee) {
        this.id = employee.getId();
        this.fullname = employee.getFullname();
        this.cpf = employee.getCpf();
        this.email = employee.getEmail();
        this.company = new CompanyDto(employee.getCompany());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }
}
