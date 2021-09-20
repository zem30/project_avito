package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ItemModeratorDao;
import com.amr.project.model.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemModeratorDaoImpl extends ReadWriteDaoImp<Item, Long> implements ItemModeratorDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Item> getUnmoderatedItems() {
        return entityManager.createQuery("SELECT i FROM Item i WHERE i.isModerateAccept = false AND i.isModerated = false", Item.class)
                .getResultList();
    }
    @Override
    public List<Item> getModeratedItems() {
        return entityManager.createQuery("SELECT i FROM Item i WHERE i.isModerateAccept = true AND i.isModerated = true ", Item.class)
                .getResultList();
    }
}
