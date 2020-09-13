package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Cart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class CartServiceIT extends BaseIntegration {

    @Autowired
    private CartService cartService;

    @Test
    public void save() {
        Cart cart = CommonModel.createCart();
        Cart saved = cartService.save(cart);
        assertNotNull(saved.getId());
        assertNotNull(saved.getTitle());
    }
}
