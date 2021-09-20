package com.amr.project.webapp.rest_controller.moderator_controller;

import com.amr.project.converter.ReviewMapper;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ItemModeratorService;
import com.amr.project.service.abstracts.ReviewModeratorService;
import com.amr.project.service.abstracts.ShopModeratorService;
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
    ReviewModeratorService reviewModeratorService;
    UserService userService;
    ShopModeratorService shopModeratorService;
    ItemModeratorService itemModeratorService;
    ReviewMapper reviewMapper;

    @Autowired
    public ReviewModeratorController(ReviewModeratorService reviewModeratorService, UserService userService, ShopModeratorService shopModeratorService,
                                     ItemModeratorService itemModeratorService, ReviewMapper reviewMapper) {
        this.reviewModeratorService = reviewModeratorService;
        this.userService = userService;
        this.shopModeratorService = shopModeratorService;
        this.itemModeratorService = itemModeratorService;
        this.reviewMapper = reviewMapper;
    }


    @GetMapping("/getUnmoderatedReviews")
    public ResponseEntity<List<ReviewDto>> getUnmodetaredReviews() {
        return new ResponseEntity<>(reviewModeratorService.
                getUnmoderatedReviews()
                .stream()
                .map(reviewMapper::reviewToDto)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/getOneUnmoderatedReview/{id}")
    public ResponseEntity<ReviewDto> getOneUnmoderatedReview(@PathVariable("id") Long id) {
        if (reviewModeratorService.getByKey(id).isModerated()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(reviewMapper.reviewToDto(reviewModeratorService.getByKey(id)));
        }
    }

    @PutMapping("/editReview")
    public ResponseEntity<ReviewDto> editReview(@RequestBody ReviewDto reviewDto) {
        Review review = reviewMapper.dtoToReview(reviewDto);
        review.setUser(userService.getByKey(reviewDto.getUserId()));
        review.setShop(shopModeratorService.getByKey(reviewDto.getShopId()));
        review.setItem(itemModeratorService.getByKey(reviewDto.getItemId()));
        reviewModeratorService.update(review);
        return ResponseEntity.ok(reviewMapper.reviewToDto(reviewModeratorService.getByKey(reviewDto.getId())));
    }

    @GetMapping("/getUnmoderatedReviewsCount")
    public ResponseEntity<Long> getUnmoderatedReviewsCount() {
        return new ResponseEntity<>((long) reviewModeratorService
                .getUnmoderatedReviews()
                .size(), HttpStatus.OK);
    }
}
