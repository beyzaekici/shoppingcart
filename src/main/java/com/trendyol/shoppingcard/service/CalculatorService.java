package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.dto.CategoryProducts;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CalculatorService {

    private CartService cartService;
    private CampaignService campaignService;
    private ProductService productService;
    private CouponService couponService;
    private CategoryService categoryService;

    @Value("9.99")
    private BigDecimal fixedDelivery = BigDecimal.valueOf(9.99);

    @Value("10.0")
    private BigDecimal fixedDeliveryPercentage = BigDecimal.valueOf(10.0);

    @Value("10.0")
    private BigDecimal fixedProductPercentage = BigDecimal.valueOf(30.0);

    @Value("100.0")
    private BigDecimal hundered = BigDecimal.valueOf(100.0);

    @Autowired
    public CalculatorService(CartService cartService, CampaignService campaignService,
                             ProductService productService, CouponService couponService, CategoryService categoryService) {
        this.cartService = cartService;
        this.campaignService = campaignService;
        this.productService = productService;
        this.couponService = couponService;
        this.categoryService = categoryService;
    }

    public BigDecimal getResultOfCalculate(String cartId) {
        Cart cart = cartService.get(cartId);
        BigDecimal discounted = BigDecimal.ZERO;
        BigDecimal totalCount = BigDecimal.ZERO;
        CategoryProducts categoryProducts = new CategoryProducts();
        categoryProducts.setTotalPrice(totalCount);
        List<String> categoriesOfCart = cartService.listCategoriesOfCart(cartId);
        List<List<Campaign>> campaignsOfCart = new ArrayList<>();
        for (String categoryTitle : categoriesOfCart) {
            List<Campaign> campaings = campaignService.listByCategory(categoryTitle);
            if (campaings.size() > 0) {
                campaignsOfCart.add(campaings);
            }
        }
        categoryProducts.setTotalPrice(calculateTotalPrice(cart.getProducts()));
        if (campaignsOfCart.size() >= 1) {
            discounted = campaignCalculation(campaignsOfCart, categoryProducts, cart, discounted);
        } else discounted = categoryProducts.getTotalPrice();
        discounted = discounted.subtract(checkCoupon(discounted));
        BigDecimal calculatedDelivery = calculateDeliveryCost(categoriesOfCart.size(), cart.getProducts().keySet().size());
        discounted = discounted.add(calculatedDelivery);
        return discounted;
    }

    public BigDecimal campaignCalculation(List<List<Campaign>> campaignsOfCart, CategoryProducts categoryProducts, Cart cart, BigDecimal discounted) {

        for (List<Campaign> campaigns : campaignsOfCart) {
            for (Campaign campaign : campaigns) {
                List<Product> products = productService.findAllByCategoryTitle(campaign.getCategory().getTitle());
                categoryProducts = calculateTotalPriceOfProductsInSameCampaign(cart, products);
                if (campaign.getQuantity() <= categoryProducts.getTotalAmount()) {
                    BigDecimal discountOfCampaign = campaign.getDiscount();
                    BigDecimal discountedAmount = categoryProducts.getTotalPrice().multiply(discountOfCampaign).divide(hundered);
                    discounted = categoryProducts.getTotalPrice().subtract(discountedAmount);
                }

            }
        }
        return discounted;
    }

    public CategoryProducts calculateTotalPriceOfProductsInSameCampaign(Cart cart, List<Product> products) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        Integer totalAmount = 0;
        CategoryProducts categoryProducts = new CategoryProducts();
        categoryProducts.setTotalAmount(totalAmount);
        for (Product product : products) {
            totalAmount = cart.getProducts().get(product.getId());
            categoryProducts.setTotalAmount(categoryProducts.getTotalAmount() + totalAmount);
            totalPrice = product.getPrice().multiply(BigDecimal.valueOf(totalAmount)).add(totalPrice);
        }
        categoryProducts.setTotalPrice(totalPrice);

        return categoryProducts;
    }

    public BigDecimal calculateTotalPrice(Map<String, Integer> products) {
        Product product;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (String productId : products.keySet()) {
            product = productService.get(productId);
            BigDecimal totalPriceOfProduct = product.getPrice().multiply(BigDecimal.valueOf(products.get(productId)));
            totalPrice = totalPrice.add(totalPriceOfProduct);
        }
        return totalPrice;
    }

    private BigDecimal checkCoupon(BigDecimal discounted) {
        Coupon coupon = couponService.getAvailableCoupon(discounted);
        BigDecimal couponPrice = BigDecimal.ZERO;
        if (coupon != null) {
            couponPrice = discounted.multiply(coupon.getDiscount()).divide(hundered);
        }

        return couponPrice;
    }

    private BigDecimal calculateDeliveryCost(Integer numberOfDeliveries, Integer numberOfProducts) {
        BigDecimal delivery = fixedDeliveryPercentage.multiply(BigDecimal.valueOf(numberOfDeliveries.longValue()));
        BigDecimal products = fixedProductPercentage.multiply(BigDecimal.valueOf(numberOfProducts.longValue()));
        return delivery.add(products).add(fixedDelivery);
    }
}
