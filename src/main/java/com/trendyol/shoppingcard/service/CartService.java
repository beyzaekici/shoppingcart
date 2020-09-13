package com.trendyol.shoppingcard.service;


import com.trendyol.shoppingcard.exception.ExceptionFactory;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CartCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CartService {

    private CartCustomRepository cartCustomRepository;

    private BaseRepository baseRepository;

    @Autowired
    public CartService(CartCustomRepository cartCustomRepository, BaseRepository baseRepository) {
        this.cartCustomRepository = cartCustomRepository;
        this.baseRepository = baseRepository;
    }

    @Transactional
    public Cart save(Cart cart) {
        if (StringUtils.isEmpty(cart.getTitle())) {
            ExceptionFactory.throwException("Title is required.", HttpStatus.BAD_REQUEST);
        }
        return baseRepository.saveOrUpdate(cart);
    }

    public Cart get(String cartId) {
        if (StringUtils.isEmpty(cartId)) {
            ExceptionFactory.throwException("CartId is required", HttpStatus.BAD_REQUEST);
        }
        Cart cart = baseRepository.get(cartId, Cart.class);
        if (cart == null) {
            ExceptionFactory.throwException("Cart not found.", HttpStatus.NOT_FOUND);
        }
        return cart;
    }

    public List<String> listCategoriesOfCart(String cartId) {
        if (StringUtils.isEmpty(cartId)) {
            ExceptionFactory.throwException("CartId is required", HttpStatus.BAD_REQUEST);
        }

        return cartCustomRepository.listCategoriesOfCart(cartId);
    }


}
