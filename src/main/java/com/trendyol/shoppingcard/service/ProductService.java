package com.trendyol.shoppingcard.service;


import com.trendyol.shoppingcard.exception.ExceptionFactory;
import com.trendyol.shoppingcard.model.Campaign;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.model.Product;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.ProductRepository;
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
public class ProductService {

    private ProductRepository productRepository;

    private CategoryService categoryService;

    private BaseRepository baseRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService, BaseRepository baseRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.baseRepository = baseRepository;
    }

    @Transactional
    public Product save(Product product) {
        if (StringUtils.isEmpty(product.getTitle())) {
            ExceptionFactory.throwException("Title is required.", HttpStatus.BAD_REQUEST);
        }

        if (product.getCategory() == null || product.getCategory().getId() == null) {
            ExceptionFactory.throwException("Category is required.", HttpStatus.BAD_REQUEST);
        }

        Category category = categoryService.get(product.getCategory().getId());
        product.setCategory(category);
        return baseRepository.saveOrUpdate(product);
    }

    public Product get(String productId) {
        if (StringUtils.isEmpty(productId)) {
            ExceptionFactory.throwException("CartId is required", HttpStatus.BAD_REQUEST);
        }
        Product product = baseRepository.get(productId, Product.class);
        if (product == null) {
            ExceptionFactory.throwException("Product not found.", HttpStatus.NOT_FOUND);
        }
        return product;
    }

    public List<Product> findAllByCategoryTitle(String categoryTitle) {
        if (StringUtils.isEmpty(categoryTitle)) {
            ExceptionFactory.throwException("categoryTitle is required", HttpStatus.BAD_REQUEST);
        }
        return productRepository.findAllByCategory_Title(categoryTitle);
    }


}
