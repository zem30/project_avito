package com.amr.project.dao.impl;

import com.amr.project.dao.abstracts.ReviewModeratorDao;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReviewModeratorDaoImpl extends ReadWriteDaoImp<Review, Long> implements ReviewModeratorDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Review> getUnmoderatedReviews() {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.isModerateAccept = false AND r.isModerated = false", Review.class)
                .getResultList();
    }
    @Override
    public List<Review> getModeratedReviews() {
        return entityManager.createQuery("SELECT r FROM Review r WHERE r.isModerateAccept = true AND r.isModerated = true ", Review.class)
                .getResultList();
    }
}
