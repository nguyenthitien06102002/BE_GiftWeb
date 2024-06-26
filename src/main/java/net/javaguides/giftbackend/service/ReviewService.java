package net.javaguides.giftbackend.service;


import net.javaguides.giftbackend.dto.OrdersDto;
import net.javaguides.giftbackend.dto.ReviewDto;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Review;
import net.javaguides.giftbackend.entity.StatusOrder;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    List<ReviewDto> getReviewsByProductId(Products products);

    List<ReviewDto> getAllReviews();
    ReviewDto getReviewById(Long reviewId);


}
