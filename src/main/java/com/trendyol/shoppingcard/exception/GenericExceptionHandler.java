package com.trendyol.shoppingcard.exception;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.common.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity handleChallengeExceptionHandler(BaseException ex) {
        ApiResponse response = new ApiResponse();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getStatusCode());
        response.setError(error);
        return new ResponseEntity<Object>(response, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleChallengeExceptionHandler(Exception ex) {
        ApiResponse response = new ApiResponse();
        ErrorResponse error = new ErrorResponse(ex.getMessage());
        response.setError(error);
        return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
