package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReviewDao;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewService {

    private ReviewDao reviewDao;

    @Autowired
    protected ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao);
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getUnmoderatedReviews() {
        return reviewDao.getUnmoderatedReviews();
    }

    @Override
    public List<Review> getModeratedReviews() {
        return reviewDao.getModeratedReviews();
    }
}
