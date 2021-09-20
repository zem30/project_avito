package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ShopModeratorDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ShopModeratorDaoImpl extends ReadWriteDaoImp<Shop, Long> implements ShopModeratorDao {
    @PersistenceContext
    EntityManager entityManager;

    public List<Shop> getUnmoderatedShops() {
        return entityManager.createQuery("SELECT s FROM Shop s WHERE s.isModerateAccept = false AND s.isModerated = false", Shop.class)
                .getResultList();
    }

    public List<Shop> getModeratedShops() {
        return entityManager.createQuery("SELECT s FROM Shop s WHERE s.isModerateAccept = true AND s.isModerated = true ", Shop.class)
                .getResultList();
    }
}
