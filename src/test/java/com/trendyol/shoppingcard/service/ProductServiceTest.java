package com.trendyol.shoppingcard.service;

import com.trendyol.shoppingcard.common.CommonModel;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Product;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.ProductRepository;
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
public class ProductServiceTest {

    @Mock
    private BaseRepository baseRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void save_shouldBe_TitleIsNull() {
        Product product = CommonModel.createProduct();
        product.setTitle(null);

        expectedException.expect(Exception.class);
        productService.save(product);
    }

    @Test
    public void save_shouldBe_CategoryIsNull() {
        Product product = CommonModel.createProduct();
        product.setCategory(null);

        expectedException.expect(Exception.class);
        productService.save(product);
    }

    @Test
    public void save_shouldBeSuccess() {
        Product product = CommonModel.createProduct();

        doReturn(product.getCategory()).when(categoryService).get(anyString());
        doReturn(product).when(baseRepository).saveOrUpdate(any());
        Product saved = productService.save(product);
        Assert.assertEquals(product.getTitle(), saved.getTitle());
    }

    @Test
    public void get_shouldBe_ProductIdIsNull() {
        expectedException.expect(Exception.class);
        productService.get(null);
    }

    @Test
    public void get_shouldBe_ProductNotFound() {
        expectedException.expect(Exception.class);

        doReturn(null).when(baseRepository).get(anyString(), any());
        productService.get("id");
    }

    @Test
    public void get_shouldBeSuccess() {
        Product product = CommonModel.createProduct();
        doReturn(product).when(baseRepository).get(anyString(), any());
        Product saved = productService.get(product.getId());
        Assert.assertEquals(product.getTitle(), saved.getTitle());
    }

    @Test
    public void findAllByCategoryTitle_shouldBeSuccess() {
        Product product = CommonModel.createProduct();
        Category category = CommonModel.createCategory();
        product.setCategory(category);
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        doReturn(productList).when(productRepository).findAllByCategory_Title(category.getTitle());
        List<Product> list = productService.findAllByCategoryTitle(category.getTitle());
        Assert.assertEquals(product.getTitle(), list.get(0).getTitle());
    }

    @Test
    public void findAllByCategoryTitle_shouldBe_CategoryTitlesAreNull() {
        expectedException.expect(Exception.class);

        productService.findAllByCategoryTitle(null);

    }
}
