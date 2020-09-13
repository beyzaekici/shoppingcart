package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Coupon;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class CouponServiceIT extends BaseIntegration {

    @Autowired
    private CouponService couponService;

    @Test
    public void save() {
        Coupon coupon = CommonModel.createCoupon();

        Coupon savedCoupon = couponService.save(coupon);
        assertNotNull(savedCoupon.getId());
        assertNotNull(savedCoupon.getTitle());
    }

}
