package com.trendyol.shoppingcard.common;

import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Coupon;
import com.trendyol.shoppingcard.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CommonModel {

    public static Campaign createCampaign() {
        Campaign campaign = new Campaign();
        campaign.setId("id");
        campaign.setTitle("title");
        campaign.setCategory(createCategory());
        campaign.setDiscount(BigDecimal.valueOf(20L));
        campaign.setQuantity(3);
        return campaign;
    }

    public static Category createCategory() {
        Category category = new Category();
        category.setId("id");
        category.setTitle("title");
        return category;
    }

    public static Coupon createCoupon() {
        Coupon coupon = new Coupon();
        coupon.setId("id");
        coupon.setTitle("title");
        coupon.setMinimumAmount(BigDecimal.valueOf(100L));
        coupon.setDiscount(BigDecimal.valueOf(10L));
        return coupon;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setId("id");
        product.setTitle("title");
        product.setPrice(BigDecimal.valueOf(10L));
        product.setCategory(createCategory());
        return product;
    }

    public static Cart createCart() {
        Cart Cart = new Cart();
        Cart.setId("id");
        Cart.setTitle("title");

        Map<String, Integer> products = new HashMap<>();
        products.put("id", 5);

        Cart.setProducts(products);
        return Cart;
    }
}
