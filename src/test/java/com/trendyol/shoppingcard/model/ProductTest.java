package com.trendyol.shoppingcard.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    Product product = new Product();
    String expectedResult = "result";
    Category category = new Category();
    BigDecimal expectedBigDecimal = BigDecimal.ONE;

    @Test
    public void testGetId() {
        product.setId(expectedResult);
        String id = product.getId();

        assertEquals(expectedResult, id);
    }

    @Test
    public void testGetTitle() {
        product.setTitle(expectedResult);
        String title = product.getTitle();
        assertEquals(expectedResult, title);
    }

    @Test
    public void testGetCategory() {
        product.setCategory(category);
        Category categoryExpected = product.getCategory();
        assertEquals(categoryExpected, category);
    }

    @Test
    public void testGetPrice() {
        product.setPrice(expectedBigDecimal);
        BigDecimal price = product.getPrice();
        assertEquals(expectedBigDecimal, price);
    }
}
