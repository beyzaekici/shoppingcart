package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.dto.AddRemoveProduct;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Product;
import com.trendyol.shoppingcard.repository.BaseRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProductsOfCartServiceTest {

    @Mock
    private BaseRepository baseRepository;

    @Mock
    private CartService cartService;

    @InjectMocks
    private ProductsOfCartService productsOfCartService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void addProduct_shouldBeError_QuantityIsNull() {
        expectedException.expect(Exception.class);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId("1");
        request.setCartId("1");
        request.setQuantity(null);

        productsOfCartService.addProductToCart(request);
    }

    @Test
    public void addProduct_shouldBeError_QuantityLessThanOne() {
        expectedException.expect(Exception.class);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId("1");
        request.setCartId("1");
        request.setQuantity(0);

        productsOfCartService.addProductToCart(request);
    }

    @Test
    public void addProduct_shouldBeSuccess() {
        Product product = CommonModel.createProduct();
        Cart cart = CommonModel.createCart();

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId(product.getId());
        request.setCartId(cart.getId());
        request.setQuantity(6);

        doReturn(cart).when(baseRepository).saveOrUpdate(any());
        doReturn(cart).when(cartService).get(anyString());

        Cart result = productsOfCartService.addProductToCart(request);
        Assert.assertNotNull(result);
    }

    @Test
    public void addProduct_shouldBeSuccess_MapIsEmpty() {
        Product product = CommonModel.createProduct();
        Cart cart = CommonModel.createCart();

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId(product.getId());
        request.setCartId(cart.getId());
        request.setQuantity(5);

        doReturn(cart).when(cartService).get(anyString());
        doReturn(cart).when(baseRepository).saveOrUpdate(any());

        Cart result = productsOfCartService.addProductToCart(request);
        Assert.assertNotNull(result);
    }

    @Test
    public void removeProduct_shouldBeError_QuantityNotFound() {
        expectedException.expect(Exception.class);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId("1");
        request.setCartId("1");

        productsOfCartService.removeProductFromCart(request);
    }

    @Test
    public void removeProduct_shouldBeError_QuantityLessThanOne() {
        expectedException.expect(Exception.class);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId("1");
        request.setCartId("1");
        request.setQuantity(0);

        productsOfCartService.removeProductFromCart(request);
    }

    @Test
    public void removeProduct_shouldBeError_ProductNotInCart() {
        expectedException.expect(Exception.class);

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId("1");
        request.setCartId("1");
        request.setQuantity(2);

        productsOfCartService.removeProductFromCart(request);
    }

    @Test
    public void removeProduct_shouldBeError_NotEnoughItemInCart() {
        expectedException.expect(Exception.class);

        Product product = CommonModel.createProduct();
        Cart shoppingCart = CommonModel.createCart();

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId(product.getId());
        request.setCartId(shoppingCart.getId());
        request.setQuantity(shoppingCart.getProducts().size() + 10);
        doReturn(shoppingCart).when(cartService).get(anyString());

        productsOfCartService.removeProductFromCart(request);
    }

    @Test
    public void removeProduct_shouldBeSuccess_MapIsEmpty() {
        Product product = CommonModel.createProduct();
        Cart cart = CommonModel.createCart();

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId(product.getId());
        request.setCartId(cart.getId());
        request.setQuantity(5);

        doReturn(cart).when(cartService).get(anyString());
        doReturn(cart).when(baseRepository).saveOrUpdate(any());

        Cart result = productsOfCartService.removeProductFromCart(request);
        Assert.assertNotNull(result);
    }

    @Test
    public void removeProduct_shouldBeSuccess() {
        Product product = CommonModel.createProduct();
        Cart cart = CommonModel.createCart();

        AddRemoveProduct request = new AddRemoveProduct();
        request.setProductId(product.getId());
        request.setCartId(cart.getId());
        request.setQuantity(1);

        doReturn(cart).when(cartService).get(anyString());
        doReturn(cart).when(baseRepository).saveOrUpdate(any());

        Cart result = productsOfCartService.removeProductFromCart(request);
        Assert.assertNotNull(result);
    }
}
