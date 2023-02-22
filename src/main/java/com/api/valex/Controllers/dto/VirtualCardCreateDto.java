package com.api.valex.Controllers.dto;

public class VirtualCardCreateDto {

    public String securityCode;

    public String password;

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
