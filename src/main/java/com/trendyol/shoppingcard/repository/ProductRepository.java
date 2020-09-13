package com.trendyol.shoppingcard.repository;

import com.trendyol.shoppingcard.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByCategory_Title(String categoryTitle);
}
