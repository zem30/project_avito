package com.amr.project.service.abstracts;

import com.amr.project.model.entity.Review;
import java.util.List;

public interface ReviewModeratorService extends ReadWriteService<Review, Long> {
    List<Review> getUnmoderatedReviews();
    List<Review> getModeratedReviews();
}
