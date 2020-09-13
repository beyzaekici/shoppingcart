package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.dto.AddRemoveProduct;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductsOfCartIT extends BaseIntegration {

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductsOfCartService productsOfCartService;

    @Test
    public void addProduct() {
        Cart cart = CommonModel.createCart();
        cart.setProducts(new HashMap<>());
        Cart savedCart = cartService.save(cart);

        Product product = CommonModel.createProduct();
        Category savedCategory = categoryService.save(product.getCategory());
        product.setCategory(savedCategory);
        Product savedProduct = productService.save(product);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setCartId(savedCart.getId());
        request.setProductId(savedProduct.getId());
        request.setQuantity(5);

        Cart result = productsOfCartService.addProductToCart(request);

        assertNotNull(result);
        assertTrue(result.getProducts().containsKey(savedProduct.getId()));
    }

    @Test
    public void removeProduct() {
        Cart cart = CommonModel.createCart();
        cart.setProducts(new HashMap<>());
        Cart savedShoppingCart = cartService.save(cart);

        Product product = CommonModel.createProduct();
        Category savedCategory = categoryService.save(product.getCategory());
        product.setCategory(savedCategory);
        Product savedProduct = productService.save(product);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setCartId(savedShoppingCart.getId());
        request.setProductId(savedProduct.getId());
        request.setQuantity(5);

        Cart result = productsOfCartService.addProductToCart(request);
        assertNotNull(result);
        assertTrue(result.getProducts().containsKey(savedProduct.getId()));

        Cart removeResult = productsOfCartService.removeProductFromCart(request);
        assertNotNull(removeResult);
        assertFalse(removeResult.getProducts().containsKey(savedProduct.getId()));
    }
}
