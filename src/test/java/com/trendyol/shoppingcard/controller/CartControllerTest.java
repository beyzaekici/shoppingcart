package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.exception.BaseException;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.service.CartService;
import com.trendyol.shoppingcard.service.ProductsOfCartService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

public class CartControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CartService cartService;

    private CartController cartController;

    private ProductsOfCartService productsOfCartService;

    @Before
    public void setUp() {
        initMocks(this);
        cartController = new CartController(cartService, productsOfCartService);
    }

    @Test
    public void save() {
        Cart cart = new Cart();
        cart.setTitle("title");

        doReturn(cart).when(cartService).save(any());

        ApiResponse<Cart> response = cartController.save(cart);

        assertEquals(cart.getTitle(), response.getData().getTitle());
    }

    @Test
    public void save_shouldBeException() {
        Cart cart = new Cart();
        cart.setTitle("title");

        doThrow(BaseException.class).when(cartService).save(any());

        expectedException.expect(BaseException.class);

        cartController.save(cart);
    }

}
