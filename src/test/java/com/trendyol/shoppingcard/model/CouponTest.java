package com.trendyol.shoppingcard.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CouponTest {

    Coupon coupon = new Coupon();
    String expectedResult = "result";

    @Test
    public void testGetId() {
        coupon.setId(expectedResult);
        String id = coupon.getId();

        assertEquals(expectedResult, id);
    }

    @Test
    public void testGetTitle() {
        coupon.setTitle(expectedResult);
        String title = coupon.getTitle();
        assertEquals(expectedResult, title);
    }
}
