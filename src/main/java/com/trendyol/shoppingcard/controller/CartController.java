package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.dto.AddRemoveProduct;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.service.CartService;
import com.trendyol.shoppingcard.service.ProductsOfCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    private ProductsOfCartService productsOfCartService;

    @Autowired
    public CartController(CartService cartService, ProductsOfCartService productsOfCartService) {
        this.cartService = cartService;
        this.productsOfCartService = productsOfCartService;
    }

    @PostMapping("/save")
    public ApiResponse<Cart> save(@RequestBody Cart cart) {
        return ApiResponse.response(cartService.save(cart));
    }

    @PostMapping("/addProduct")
    public ApiResponse<Cart> addProduct(@RequestBody AddRemoveProduct request) {
        return ApiResponse.response(productsOfCartService.addProductToCart(request));
    }

    @PostMapping("/removeProduct")
    public ApiResponse<Cart> removeProduct(@RequestBody AddRemoveProduct request) {
        return ApiResponse.response(productsOfCartService.removeProductFromCart(request));
    }

}
