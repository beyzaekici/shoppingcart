package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.exception.BaseException;
import com.trendyol.shoppingcard.model.Product;
import com.trendyol.shoppingcard.service.ProductService;
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

public class ProductControllerTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ProductService productService;

    private ProductController productController;

    @Before
    public void setUp() {
        initMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    public void save() {
        Product product = new Product();
        product.setTitle("title");

        doReturn(product).when(productService).save(any());

        ApiResponse<Product> response = productController.save(product);

        assertEquals(product.getTitle(), response.getData().getTitle());
    }

    @Test
    public void save_shouldBeException() {
        Product product = new Product();
        product.setTitle("title");

        doThrow(BaseException.class).when(productService).save(any());

        expectedException.expect(BaseException.class);

        productController.save(product);
    }
}
