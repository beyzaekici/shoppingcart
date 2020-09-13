package com.trendyol.shoppingcard.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CartCustomRepository extends BaseRepository {

    public List<String> listCategoriesOfCart(String cartId) {
        String sql = "select DISTINCT  category.title from category join product p on category.id = p.category_id" +
                " join cart_products cp on p.id = cp.product_id where cp.id=:cartId";
        Query query = entityManager.createNativeQuery(sql);
        if (cartId != null) {
            query.setParameter("cartId", cartId);
        }

        return query.getResultList();
    }
}
