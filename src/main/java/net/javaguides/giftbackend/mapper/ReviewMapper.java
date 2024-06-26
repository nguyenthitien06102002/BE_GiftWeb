package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ReviewDto;
import net.javaguides.giftbackend.entity.Review;

public class ReviewMapper {
    public static ReviewDto mapToReviewDto(Review review){
        return new ReviewDto(
                review.getId(),
                review.getUserId(),
                review.getProductId(),
                review.getOrdersId(),
                review.getRating(),
                review.getContent(),
                review.getCreateDate(),
                review.getStatus(),
                review.getIp()
        );
    }
    public static Review mapToReview(ReviewDto reviewDto){
        return new Review(
                reviewDto.getId(),
                reviewDto.getUserId(),
                reviewDto.getProductId(),
                reviewDto.getOrdersId(),
                reviewDto.getRating(),
                reviewDto.getContent(),
                reviewDto.getCreateDate(),
                reviewDto.getStatus(),
                reviewDto.getIp()
        );
    }





























































































































































































}
