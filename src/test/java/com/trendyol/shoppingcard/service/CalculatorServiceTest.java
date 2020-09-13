package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.dto.CategoryProducts;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.model.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    @Mock
    private CartService cartService;

    @Mock
    private ProductService productService;

    @Mock
    private CouponService couponService;

    @InjectMocks
    private CalculatorService calculatorService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getResultOfCalculate_shoulBeSucces() {
        Cart cart = CommonModel.createCart();
        Product product = CommonModel.createProduct();
        Coupon coupon = CommonModel.createCoupon();
        Category category = CommonModel.createCategory();
        Campaign campaign = CommonModel.createCampaign();
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        product.setCategory(category);
        doReturn(cart).when(cartService).get(anyString());
        doReturn(product).when(productService).get(anyString());
        doReturn(coupon).when(couponService).getAvailableCoupon(any());

        calculatorService.getResultOfCalculate(cart.getId());
    }

    @Test
    public void campaignCalculation_shouldBe_success() {
        BigDecimal hundered = new BigDecimal(100);
        BigDecimal discount = new BigDecimal(300);
        Integer totalAmount = 200;
        Campaign campaign = CommonModel.createCampaign();
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(campaign);
        List<List<Campaign>> lists = new ArrayList<>();
        lists.add(campaigns);

        CategoryProducts categoryProducts = new CategoryProducts();
        categoryProducts.setTotalPrice(hundered);
        categoryProducts.setTotalAmount(totalAmount);

        Cart cart = CommonModel.createCart();
        Product product = CommonModel.createProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        doReturn(products).when(productService).findAllByCategoryTitle(anyString());

        calculatorService.campaignCalculation(lists, categoryProducts, cart, discount);

    }
}
