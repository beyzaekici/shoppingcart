package com.trendyol.shoppingcard.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CartTest {

    Cart cart = new Cart();
    String expectedResult = "result";

    @Test
    public void testGetCartId() {
        cart.setId(expectedResult);
        String id = cart.getId();

        assertEquals(expectedResult, id);
    }

    @Test
    public void testGetQuantity() {
        cart.setTitle(expectedResult);
        String title = cart.getTitle();

        assertEquals(expectedResult, title);
    }

}
