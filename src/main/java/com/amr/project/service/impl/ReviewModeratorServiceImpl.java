package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.ReviewModeratorDao;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewModeratorServiceImpl extends ReadWriteServiceImpl<Review, Long> implements ReviewModeratorService {
    private ReviewModeratorDao reviewModeratorDao;
@Autowired
    protected ReviewModeratorServiceImpl(ReviewModeratorDao reviewModeratorDao) {
        super(reviewModeratorDao);
        this.reviewModeratorDao = reviewModeratorDao;
    }

    @Override
    public List<Review> getUnmoderatedReviews() {
        return reviewModeratorDao.getUnmoderatedReviews();
    }

    @Override
    public List<Review> getModeratedReviews() {
        return reviewModeratorDao.getModeratedReviews();
    }
}
