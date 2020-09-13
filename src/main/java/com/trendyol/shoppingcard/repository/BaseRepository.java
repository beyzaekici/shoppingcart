package com.trendyol.shoppingcard.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceProperty;
import java.io.Serializable;

@Repository
public class BaseRepository {
    @PersistenceContext(properties = {@PersistenceProperty(name = "hibernate.max_fetch_depth", value = "1"), @PersistenceProperty(name = "hibernate.enable_lazy_load_no_trans", value = "true")})
    public EntityManager entityManager;

    public <T> T saveOrUpdate(T entity) {
        return entityManager.merge(entity);
    }

    public <T> T get(Serializable id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

}

