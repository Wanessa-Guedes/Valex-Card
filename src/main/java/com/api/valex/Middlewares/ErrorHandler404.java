package com.api.valex.Middlewares;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErrorHandler404 extends Exception {
    private String code;
    public ErrorHandler404(String code, String message){
        super(message);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
