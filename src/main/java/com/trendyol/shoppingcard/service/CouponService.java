package com.trendyol.shoppingcard.service;


import com.trendyol.shoppingcard.exception.ExceptionFactory;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CouponCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CouponService {

    private BaseRepository baseRepository;

    private CouponCustomRepository couponCustomRepository;

    @Autowired
    public CouponService(BaseRepository baseRepository, CouponCustomRepository couponCustomRepository) {
        this.baseRepository = baseRepository;
        this.couponCustomRepository = couponCustomRepository;
    }

    @Transactional
    public Coupon save(Coupon coupon) {
        if (StringUtils.isEmpty(coupon.getTitle())) {
            ExceptionFactory.throwException("Title is required.", HttpStatus.BAD_REQUEST);
        }
        if (coupon.getMinimumAmount() == null) {
            ExceptionFactory.throwException("MinimumAmount is required.", HttpStatus.BAD_REQUEST);
        }
        if (coupon.getDiscount() == null) {
            ExceptionFactory.throwException("Discount is required.", HttpStatus.BAD_REQUEST);
        }
        return baseRepository.saveOrUpdate(coupon);
    }

    public Coupon getAvailableCoupon(BigDecimal minimumAmount) {
        if (minimumAmount == null) {
            ExceptionFactory.throwException("MinimumAmount is required.", HttpStatus.BAD_REQUEST);
        }
        return couponCustomRepository.getAvailableCoupon(minimumAmount);
    }
}
