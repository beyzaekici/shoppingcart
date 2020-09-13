package com.trendyol.shoppingcard.service;


import com.trendyol.shoppingcard.exception.ExceptionFactory;
import com.trendyol.shoppingcard.model.Category;
import com.trendyol.shoppingcard.repository.BaseRepository;
import com.trendyol.shoppingcard.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly = true, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class CategoryService {

    private CategoryRepository categoryRepository;

    private BaseRepository baseRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, BaseRepository baseRepository) {
        this.categoryRepository = categoryRepository;
        this.baseRepository = baseRepository;
    }

    @Transactional
    public Category save(Category category) {
        if (StringUtils.isEmpty(category.getTitle())) {
            ExceptionFactory.throwException("Title is required.", HttpStatus.BAD_REQUEST);
        }
        return baseRepository.saveOrUpdate(category);
    }

    public Category get(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionFactory.throwException("id is required", HttpStatus.BAD_REQUEST);
        }
        Category category = baseRepository.get(id, Category.class);
        if (category == null) {
            ExceptionFactory.throwException("Category not found.", HttpStatus.NOT_FOUND);
        }
        return category;
    }

}
