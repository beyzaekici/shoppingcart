package com.trendyol.shoppingcard.exception;

import org.springframework.http.HttpStatus;

public class ExceptionFactory {

    private ExceptionFactory() {
    }

    public static void throwException(String message, HttpStatus httpStatus) {
        throw new BaseException(message, httpStatus);
    }
}
