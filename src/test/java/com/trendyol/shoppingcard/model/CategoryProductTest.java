package com.trendyol.shoppingcard.model;

import com.trendyol.shoppingcard.dto.CategoryProducts;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CategoryProductTest {

    CategoryProducts categoryProducts = new CategoryProducts();
    Integer expectedInteger = 1;
    BigDecimal expectedBigDecimal = BigDecimal.ONE;

    @Test
    public void testGetCartTotalAmount() {
        categoryProducts.setTotalAmount(expectedInteger);
        Integer categoryProductsTotalAmount = categoryProducts.getTotalAmount();

        assertEquals(expectedInteger, categoryProductsTotalAmount);
    }

    @Test
    public void testGetTotalPrice() {
        categoryProducts.setTotalPrice(expectedBigDecimal);
        BigDecimal categoryProductsTotalPrice = categoryProducts.getTotalPrice();
        assertEquals(expectedBigDecimal, categoryProductsTotalPrice);
    }

}
