package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.dto.AddRemoveProduct;
import com.trendyol.shoppingcard.exception.ExceptionFactory;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ProductsOfCartService {

    private CartService cartService;

    private BaseRepository baseRepository;

    @Autowired
    public ProductsOfCartService(CartService cartService, BaseRepository baseRepository) {
        this.cartService = cartService;
        this.baseRepository = baseRepository;
    }

    @Transactional
    public Cart addProductToCart(AddRemoveProduct addProduct) {
        validate(addProduct);
        Cart cart = cartService.get(addProduct.getCartId());
        String productId = addProduct.getProductId();
        Integer quantity = addProduct.getQuantity();

        if (quantity == null) {
            ExceptionFactory.throwException("Quantity is required.", HttpStatus.BAD_REQUEST);
        }

        Map<String, Integer> products = cart.getProducts();
        if (products.get(productId) != null) {
            quantity += products.get(productId);
        }

        products.put(productId, quantity);
        cart.setProducts(products);
        return baseRepository.saveOrUpdate(cart);
    }

    @Transactional
    public Cart removeProductFromCart(AddRemoveProduct removeProduct) {

        validate(removeProduct);
        Cart cart = cartService.get(removeProduct.getCartId());
        String productId = removeProduct.getProductId();
        Integer quantity = removeProduct.getQuantity();

        Map<String, Integer> products = cart.getProducts();
        if (products.get(productId) == null) {
            ExceptionFactory.throwException("Product not found", HttpStatus.BAD_REQUEST);
        }

        if (products.get(productId) < quantity) {
            ExceptionFactory.throwException("There is no enough products for your request.", HttpStatus.BAD_REQUEST);
        }

        quantity = products.get(productId) - quantity;

        if (quantity.equals(0)) {
            products.remove(productId);
        } else {
            products.put(productId, quantity);
        }

        cart.setProducts(products);
        return baseRepository.saveOrUpdate(cart);
    }

    public void validate(AddRemoveProduct addRemoveProduct) {

        if (addRemoveProduct.getQuantity() == null) {
            ExceptionFactory.throwException("Quantity is required.", HttpStatus.BAD_REQUEST);
        }

        if (addRemoveProduct.getQuantity() < 1) {
            ExceptionFactory.throwException("Quantity must be greater than 1", HttpStatus.BAD_REQUEST);
        }
    }
}
