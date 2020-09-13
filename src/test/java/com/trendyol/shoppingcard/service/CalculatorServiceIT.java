package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.dto.AddRemoveProduct;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

public class CalculatorServiceIT extends BaseIntegration {

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private ProductsOfCartService productsOfCartService;

    @Value("${fixedDelivery}")
    private BigDecimal fixedDelivery = BigDecimal.valueOf(9.99);

    @Value("${fixedDeliveryPercentage}")
    private BigDecimal fixedDeliveryPercentage = BigDecimal.valueOf(10.0);

    @Value("${fixedProductPercentage}")
    private BigDecimal fixedProductPercentage = BigDecimal.valueOf(10.0);

    @Value("${hundered}")
    private BigDecimal hundered = BigDecimal.valueOf(100.0);

    @Test
    public void getResultOfCalculate() {
        Category category = getCategory();
        BigDecimal totalAmount = BigDecimal.ZERO;
        Integer totalQuantity = 0;
        BigDecimal dicountOfCampaign = BigDecimal.ZERO;

        Product savedProduct1 = getProduct("product1", category);
        Product savedProduct2 = getProduct("product2", category);
        Product savedProduct3 = getProduct("product3", category);

        Campaign savedCampaign = getCampaign(category);

        Coupon savedCoupon = couponService.save(CommonModel.createCoupon());

        Cart cart = CommonModel.createCart();
        cart.setProducts(new HashMap<>());
        Cart savedCart = cartService.save(cart);

        savedCart = addProduct(savedCart.getId(), savedProduct1.getId(), 5);
        savedCart = addProduct(savedCart.getId(), savedProduct2.getId(), 3);
        savedCart = addProduct(savedCart.getId(), savedProduct3.getId(), 7);

        BigDecimal campaignDiscount = BigDecimal.ZERO;
        totalQuantity = getQuantityInCart(savedCart, totalQuantity);

        if (totalQuantity > savedCampaign.getQuantity()) {
            for (String key : savedCart.getProducts().keySet()) {
                Product cartProduct = productService.get(key);
                Integer quantity = savedCart.getProducts().get(key);
                BigDecimal totalPrice = cartProduct.getPrice().multiply(BigDecimal.valueOf(quantity));
                BigDecimal discount;

                BigDecimal discounted = totalPrice.multiply(savedCampaign.getDiscount());
                discount = discounted.divide(hundered);
                campaignDiscount = campaignDiscount.add(discount);
                totalAmount = totalAmount.add(totalPrice.subtract(discount));

            }

        } else {
            totalAmount = getTotalAmountNoCampaign(savedCart, totalAmount);
        }

        BigDecimal couponDiscount;
        if (totalAmount.compareTo(savedCoupon.getMinimumAmount()) > 0) {

            couponDiscount = totalAmount.multiply(savedCoupon.getDiscount()).divide(BigDecimal.valueOf(100L));
            totalAmount = totalAmount.subtract(couponDiscount);
        }

        BigDecimal response = calculatorService.getResultOfCalculate(savedCart.getId());
        assertNotNull(response);
        assertNotNull(response.compareTo(BigDecimal.valueOf(157.99)) == 0);


    }

    private Category getCategory() {
        return categoryService.save(CommonModel.createCategory());
    }

    private Campaign getCampaign(Category category) {
        Campaign campaign = CommonModel.createCampaign();
        campaign.setCategory(category);
        return campaignService.save(campaign);
    }

    private Product getProduct(String productTitle, Category category) {
        Product product = CommonModel.createProduct();
        product.setTitle(productTitle);
        product.setCategory(category);
        return productService.save(product);
    }

    private Cart addProduct(String cartId, String productId, Integer quantity) {
        AddRemoveProduct request = new AddRemoveProduct();
        request.setCartId(cartId);
        request.setProductId(productId);
        request.setQuantity(quantity);

        return productsOfCartService.addProductToCart(request);
    }

    private Integer getQuantityInCart(Cart cart, Integer totalQuantity) {
        for (String key : cart.getProducts().keySet()) {
            totalQuantity += cart.getProducts().get(key);
        }
        return totalQuantity;
    }

    private BigDecimal getTotalAmountNoCampaign(Cart cart, BigDecimal totalAmount) {
        for (String key : cart.getProducts().keySet()) {
            Product cartProduct = productService.get(key);
            Integer quantity = cart.getProducts().get(key);
            totalAmount = totalAmount.add(cartProduct.getPrice().multiply(BigDecimal.valueOf(quantity.longValue())));
        }
        return totalAmount;
    }
}
