package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @Mock
    private BaseRepository baseRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void save_shouldBeError_TitleIsNull() {
        Category category = CommonModel.createCategory();
        category.setTitle(null);

        expectedException.expect(Exception.class);
        categoryService.save(category);
    }

    @Test
    public void save_shouldBeSuccess() {
        Category category = CommonModel.createCategory();

        doReturn(category).when(baseRepository).saveOrUpdate(any());
        Category saved = categoryService.save(category);
        Assert.assertEquals(category.getTitle(), saved.getTitle());
    }

    @Test
    public void get_shouldBeError_CategoryIdIsNull() {
        expectedException.expect(Exception.class);
        categoryService.get(null);
    }

    @Test
    public void get_shouldBeError_CategoryNotFound() {
        expectedException.expect(Exception.class);

        doReturn(null).when(baseRepository).get(anyString(), any());
        categoryService.get("id");
    }

    @Test
    public void get_shouldBeSuccess() {
        Category category = CommonModel.createCategory();
        doReturn(category).when(baseRepository).get(anyString(), any());
        Category saved = categoryService.get(category.getId());
        Assert.assertEquals(category.getTitle(), saved.getTitle());
    }
}


