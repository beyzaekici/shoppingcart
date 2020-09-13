package com.trendyol.shoppingcard.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private HttpStatus statusCode;
    private String message;

    public BaseException(String message, HttpStatus statusCode) {
        super();
        this.message = message;
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
