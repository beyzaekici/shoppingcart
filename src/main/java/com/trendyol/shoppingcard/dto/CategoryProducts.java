package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

public class CategoryProducts {

    private BigDecimal totalPrice;
    private Integer totalAmount;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
