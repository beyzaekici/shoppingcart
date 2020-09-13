package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.repository.CouponCustomRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CouponServiceTest {

    @Mock
    private CouponCustomRepository couponCustomRepository;

    @InjectMocks
    private CouponService couponService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void save_shouldBeError_TitleIsNull() {
        Coupon coupon = CommonModel.createCoupon();
        coupon.setTitle(null);

        expectedException.expect(Exception.class);
        couponService.save(coupon);
    }

    @Test
    public void save_shouldBeError_MinimumAmountIsNull() {
        Coupon coupon = CommonModel.createCoupon();
        coupon.setMinimumAmount(null);

        expectedException.expect(Exception.class);
        couponService.save(coupon);
    }

    @Test
    public void save_shouldBeError_DiscountIsNull() {
        Coupon coupon = CommonModel.createCoupon();
        coupon.setDiscount(null);

        expectedException.expect(Exception.class);
        couponService.save(coupon);
    }

    @Test
    public void save_shouldBeSuccess() {
        Coupon coupon = CommonModel.createCoupon();

        doReturn(coupon).when(couponCustomRepository).saveOrUpdate(any(Coupon.class));
        Coupon saved = couponService.save(coupon);
        Assert.assertEquals(coupon.getTitle(), saved.getTitle());
    }

    @Test
    public void getAvailableCoupon_shouldBeError_MinimumAmountIsNull() {
        expectedException.expect(Exception.class);
        couponService.getAvailableCoupon(null);
    }

    @Test
    public void getAvailableCoupon_shouldBeSuccess() {
        Coupon coupon = CommonModel.createCoupon();
        doReturn(coupon).when(couponCustomRepository).getAvailableCoupon(any());
        Coupon result = couponService.getAvailableCoupon(coupon.getMinimumAmount());
        Assert.assertEquals(coupon.getTitle(), result.getTitle());
    }
}
