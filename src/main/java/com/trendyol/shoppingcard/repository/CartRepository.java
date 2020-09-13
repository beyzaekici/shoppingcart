package com.trendyol.shoppingcard.repository;

import com.trendyol.shoppingcard.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

}
