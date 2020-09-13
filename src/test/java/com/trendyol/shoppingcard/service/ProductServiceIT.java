package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductServiceIT extends BaseIntegration {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Test
    public void save() {
        Product product = CommonModel.createProduct();
        Category savedCategory = categoryService.save(product.getCategory());
        product.setCategory(savedCategory);

        Product savedProduct = productService.save(product);
        assertNotNull(savedProduct.getId());
        assertNotNull(savedProduct.getTitle());
    }

    @Test
    public void get() {
        Product product = CommonModel.createProduct();
        Category savedCategory = categoryService.save(product.getCategory());
        product.setCategory(savedCategory);
        Product savedProduct = productService.save(product);

        Product result = productService.get(savedProduct.getId());

        assertEquals(savedProduct.getId(), result.getId());
        assertEquals(savedProduct.getTitle(), result.getTitle());
    }
}
