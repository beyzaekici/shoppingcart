package com.trendyol.shoppingcard.controller;

import com.trendyol.shoppingcard.common.ApiResponse;
import com.trendyol.shoppingcard.exception.BaseException;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.service.CategoryService;
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

public class CategoryControllerTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CategoryService categoryService;

    private CategoryController categoryController;

    @Before
    public void setUp() {
        initMocks(this);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    public void save() {
        Category category = new Category();
        category.setTitle("title");

        doReturn(category).when(categoryService).save(any());

        ApiResponse<Category> response = categoryController.save(category);

        assertEquals(category.getTitle(), response.getData().getTitle());
    }

    @Test
    public void save_shouldBeException() {
        Category category = new Category();
        category.setTitle("title");

        doThrow(BaseException.class).when(categoryService).save(any());

        expectedException.expect(BaseException.class);

        categoryController.save(category);
    }
}
