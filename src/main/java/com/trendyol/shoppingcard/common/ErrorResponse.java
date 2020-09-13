package com.trendyol.shoppingcard.common;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private HttpStatus errorCode;
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(final String message, final HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
