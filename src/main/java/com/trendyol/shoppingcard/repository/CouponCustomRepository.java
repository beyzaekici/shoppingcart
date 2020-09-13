package com.trendyol.shoppingcard.repository;

import com.trendyol.shoppingcard.model.Coupon;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class CouponCustomRepository extends BaseRepository {

    public Coupon getAvailableCoupon(BigDecimal minimumAmount) {
        StringBuilder hql = new StringBuilder();
        hql.append(" from Coupon c where c.minimumAmount < :minimumAmount order by c.minimumAmount desc");

        Query query = entityManager.createQuery(hql.toString());

        query.setParameter("minimumAmount", minimumAmount);

        query.setFirstResult(0);
        query.setMaxResults(1);

        List resultList = query.getResultList();
        return resultList.size() > 0 ? (Coupon) resultList.get(0) : null;
    }

}
