package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Cart;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CartCustomRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private BaseRepository baseRepository;

    @Mock
    private CartCustomRepository cartCustomRepository;

    @InjectMocks
    private CartService cartService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void save_shouldBeError_TitleIsNull() {
        Cart cart = CommonModel.createCart();
        cart.setTitle(null);

        expectedException.expect(Exception.class);
        cartService.save(cart);
    }

    @Test
    public void save_shouldBeSuccess() {
        Cart cart = CommonModel.createCart();
        Cart saved = cartService.save(cart);
        Assert.assertNull(saved);
    }

    @Test
    public void get_shouldBeError_CartIdIsNull() {
        expectedException.expect(Exception.class);
        cartService.get(null);
    }

    @Test
    public void get_shouldBeError_CartNotFound() {
        expectedException.expect(Exception.class);

        doReturn(null).when(baseRepository).get(anyString(), any());
        cartService.get("id");
    }

    @Test
    public void get_shouldBeSuccess() {
        Cart cart = CommonModel.createCart();
        doReturn(cart).when(baseRepository).get(cart.getId(), Cart.class);
        Cart saved = cartService.get(cart.getId());
        Assert.assertEquals(cart.getTitle(), saved.getTitle());
    }

    @Test
    public void listCategoriesOfCart_shouldBeSuccess() {
        Category category = CommonModel.createCategory();
        List<String> strings = new ArrayList<>();
        strings.add(category.getTitle());
        doReturn(strings).when(cartCustomRepository).listCategoriesOfCart(category.getTitle());
        List<String> list = cartService.listCategoriesOfCart(category.getTitle());
        Assert.assertEquals(category.getTitle(), list.get(0));
    }

    @Test
    public void listCategoriesOfCart_shouldBeError_CartIdNull() {
        expectedException.expect(Exception.class);

        cartService.listCategoriesOfCart(null);

    }
}
