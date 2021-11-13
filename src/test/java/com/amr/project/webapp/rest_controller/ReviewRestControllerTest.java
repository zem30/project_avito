package com.amr.project.webapp.rest_controller;

import com.amr.project.AbstractApiTest;
import com.amr.project.converter.ReviewMapper;
import com.amr.project.model.dto.ReviewDto;
import com.amr.project.model.entity.Review;
import com.amr.project.service.abstracts.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.GregorianCalendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ReviewRestControllerTest extends AbstractApiTest {

    private final static String ADD_REVIEW_FOR_ITEM_URL = "/api/review/item";
    private final static String ADD_REVIEW_FOR_SHOP_URL = "/api/review/shop";
    private final static String GET_REVIEW_BY_ID_URL = "/api/review/1";
    private final static String GET_ALL_ITEMS_REVIEW_URL = "/api/review/items";
    private final static String GET_ALL_SHOPS_REVIEW_URL = "/api/review/shops";


    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewRestControllerTest(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @Test
    void shouldBeAddReviewItem() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .dignity("dignity_review_user4")
                .flaw("flaw_review_user4")
                .text("text_review_user4")
                .date(GregorianCalendar.getInstance().getTime())
                .rating(4)
                .itemName("item1")
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .logo(null)
                .build();
        Review review = reviewMapper.dtoToReview(reviewDto);
        mvc.perform(
                        post(ADD_REVIEW_FOR_ITEM_URL)
                                .content(asJsonString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void notShouldBeAddReviewItem() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .dignity("dignity_review_user4")
                .text("text_review_user4")
                .date(GregorianCalendar.getInstance().getTime())
                .rating(4)
                .itemName("item1")
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .logo(null)
                .build();
        Review review = reviewMapper.dtoToReview(reviewDto);
        mvc.perform(
                        post(ADD_REVIEW_FOR_ITEM_URL)
                                .content(asJsonString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldBeAddReviewShop() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .dignity("dignity_review_user4")
                .flaw("flaw_review_user4")
                .text("text_review_user4")
                .date(GregorianCalendar.getInstance().getTime())
                .rating(4)
                .itemName("shop1")
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .logo(null)
                .build();
        Review review = reviewMapper.dtoToReview(reviewDto);
        mvc.perform(
                        post(ADD_REVIEW_FOR_SHOP_URL)
                                .content(asJsonString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void notShouldBeAddReviewShop() throws Exception {
        ReviewDto reviewDto = ReviewDto.builder()
                .flaw("flaw_review_user4")
                .text("text_review_user4")
                .date(GregorianCalendar.getInstance().getTime())
                .rating(4)
                .itemName("shop1")
                .isModerated(false)
                .isModerateAccept(false)
                .moderatedRejectReason(null)
                .logo(null)
                .build();
        Review review = reviewMapper.dtoToReview(reviewDto);
        mvc.perform(
                        post(ADD_REVIEW_FOR_SHOP_URL)
                                .content(asJsonString(review))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldBeGetReviewById() throws Exception {
        mvc.perform(get(GET_REVIEW_BY_ID_URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldBeGetAllReviewItems() throws Exception {
        mvc.perform(get(GET_ALL_ITEMS_REVIEW_URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldBeGetAllReviewShops() throws Exception {
        mvc.perform(get(GET_ALL_SHOPS_REVIEW_URL))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}