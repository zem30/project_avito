package com.amr.project.webapp.rest_controller.moderator_controller;

import com.amr.project.converter.ReviewMapper;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Item;
import com.amr.project.model.entity.Review;
import com.amr.project.model.entity.Shop;
import com.amr.project.service.abstracts.ItemService;
import com.amr.project.service.abstracts.ReviewService;
import com.amr.project.service.abstracts.ShopService;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/moderator/api/reviews")
public class ReviewModeratorController {
    ReviewService reviewService;
    UserService userService;
    ShopService shopService;
    ItemService itemService;
    ReviewMapper reviewMapper;

    @Autowired
    public ReviewModeratorController(ReviewService reviewService, UserService userService, ShopService shopService,
                                     ItemService itemService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.shopService = shopService;
        this.itemService = itemService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/getUnmoderatedReviews")
    public ResponseEntity<List<ReviewDto>> getUnmodetaredReviews() {
        return new ResponseEntity<>(reviewService.
                getUnmoderatedReviews()
                .stream()
                .map(reviewMapper::reviewToDto)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/getOneUnmoderatedReview/{id}")
    public ResponseEntity<ReviewDto> getOneUnmoderatedReview(@PathVariable("id") Long id) {
        if (reviewService.getByKey(id).isModerated()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(reviewMapper.reviewToDto(reviewService.getByKey(id)));
        }
    }

    @PutMapping("/editReview")
    public ResponseEntity<ReviewDto> editReview(@RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToReview(reviewDto);

        review.setUser(userService.getByKey(reviewDto.getUserId()));
        if (reviewDto.getShopId() != null) {
            review.setShop(shopService.getByKey(reviewDto.getShopId()));
        }

        if (reviewDto.getItemId() != null){
            review.setItem(itemService.getByKey(reviewDto.getItemId()));
        }
        reviewService.update(review);
        return ResponseEntity.ok(reviewMapper.reviewToDto(reviewService.getByKey(reviewDto.getId())));
    }

    @GetMapping("/getUnmoderatedReviewsCount")
    public ResponseEntity<Long> getUnmoderatedReviewsCount() {
        return new ResponseEntity<>((long) reviewService
                .getUnmoderatedReviews()
                .size(), HttpStatus.OK);
    }
}