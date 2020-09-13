package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.base.BaseIntegration;
import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryServiceIT extends BaseIntegration {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void save() {
        Category category = CommonModel.createCategory();
        Category savedCategory = categoryService.save(category);
        assertNotNull(savedCategory.getId());
        assertNotNull(savedCategory.getTitle());
    }

    @Test
    public void get() {
        Category savedCategory = categoryService.save(CommonModel.createCategory());

        Category result = categoryService.get(savedCategory.getId());

        assertEquals(savedCategory.getId(), result.getId());
        assertEquals(savedCategory.getTitle(), result.getTitle());
    }
}
