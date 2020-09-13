package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/save")
    public ApiResponse<Coupon> save(@RequestBody Coupon coupon) {
        return ApiResponse.response(couponService.save(coupon));
    }

    @GetMapping("/")
    public ApiResponse<Coupon> getAvaibleCoupon(BigDecimal minimumAmount) {
        return ApiResponse.response(couponService.getAvailableCoupon(minimumAmount));
    }
}
