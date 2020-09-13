package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.exception.BaseException;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.service.CouponService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

public class CouponControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CouponService couponService;

    private CouponController couponController;

    @Before
    public void setUp() {
        initMocks(this);
        couponController = new CouponController(couponService);
    }

    @Test
    public void save() {
        Coupon coupon = new Coupon();
        coupon.setTitle("title");

        doReturn(coupon).when(couponService).save(any());

        ApiResponse<Coupon> response = couponController.save(coupon);

        assertEquals(coupon.getTitle(), response.getData().getTitle());
    }

    @Test
    public void save_shouldBeException() {
        Coupon coupon = new Coupon();
        coupon.setTitle("title");

        doThrow(BaseException.class).when(couponService).save(any());

        expectedException.expect(BaseException.class);

        couponController.save(coupon);
    }
}
