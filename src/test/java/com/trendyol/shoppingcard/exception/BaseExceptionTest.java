package com.trendyol.shoppingcard.exception;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseExceptionTest {
    private BaseException baseException;

    @Before
    public void setUp() {
        baseException = new BaseException("message", HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getStatusCode() {
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        baseException.setStatusCode(expected);
        assertEquals(expected, baseException.getStatusCode());
    }

    @Test
    public void getMessage() {
        String expected = "test";
        baseException.setMessage(expected);
        assertEquals(expected, baseException.getMessage());
    }
}
