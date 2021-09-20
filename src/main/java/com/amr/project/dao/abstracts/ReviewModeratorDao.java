package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Review;

import java.util.List;

public interface ReviewModeratorDao extends ReadWriteDao<Review, Long> {
    List<Review> getUnmoderatedReviews();
    List<Review> getModeratedReviews();
    }
