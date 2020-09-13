package com.trendyol.shoppingcard.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> implements Serializable {

    private T data;
    private ErrorResponse error;
    private String time = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toString();

    public ApiResponse() {
    }

    public static <E> ApiResponse<E> response() {
        return new ApiResponse();
    }

    public static <E> ApiResponse<E> response(E data) {
        ApiResponse<E> response = new ApiResponse();
        response.data = data;
        return response;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
