package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.OrdersDto;
import net.javaguides.giftbackend.dto.ReviewDto;
import net.javaguides.giftbackend.entity.*;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.mapper.OrdersMapper;
import net.javaguides.giftbackend.mapper.ReviewMapper;
import net.javaguides.giftbackend.repository.*;
import net.javaguides.giftbackend.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private UsersRepository usersRepository;
    private ProductsRepository productsRepository;
    private ReviewRepository reviewRepository;
    private OrdersRepository ordersRepository;
    private StatusOrderRepository statusRepository;


    @Override
    public ReviewDto createReview( ReviewDto reviewDto) {
        Long userId = reviewDto.getUserId().getId();
        long statusId = 4L;
        Long productId = reviewDto.getProductId().getId();
        Long orderId = reviewDto.getOrdersId().getId();
        Users users = usersRepository.findById(userId).orElse(null);
        StatusOrder statusOrder = statusRepository.findById(statusId).orElse(null);
        Products products = productsRepository.findById(productId).orElse(null);
        Orders o = ordersRepository.findById(orderId).orElse(null);
        if(users == null || products == null || orderId == null ){
            return null;
        }
        Review review = ReviewMapper.mapToReview(reviewDto);
        review.setUserId(users);
        review.setProductId(products);
        review.setOrdersId(o);
        Review saveReview = reviewRepository.save(review);
        updateStatusOrder(orderId, statusOrder);
        return ReviewMapper.mapToReviewDto(saveReview);
    }

    @Override
    public List<ReviewDto> getReviewsByProductId(Products products) {
        List<Review> reviews = reviewRepository.findByProductId(products);
        if(reviews.isEmpty()){
            throw new ResourceNotFoundException("No orders found for user with ID: " + products.getId());
        }
        return reviews.stream().map(ReviewMapper::mapToReviewDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        if(reviews.isEmpty()){
            throw new ResourceNotFoundException("No orders found for user with ID: ");
        }
        return reviews.stream().map(ReviewMapper::mapToReviewDto).collect(Collectors.toList());

    }

    @Override
    public ReviewDto getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + reviewId));
        return ReviewMapper.mapToReviewDto(review);
    }


    public OrdersDto updateStatusOrder(Long orderId, StatusOrder statusId){
//        Long id = orderId.getId();
        Orders orders = ordersRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        orders.setStatus(statusId);
        Orders updatedOrderObj = ordersRepository.save(orders);
        return OrdersMapper.mapToOrdersDto(updatedOrderObj);
    }


    //    public OrdersDto updateStatusOrder(long orderId, OrdersDto updateStatusOrder) {
//
//        Order order =  OrderRepository.findById(orderId).orElseThrow(
//                () -> new ResourceNotFoundException("User not found with id: " + orderId));
//        order.setStatus(4);
//        Order updatedOrderObj = OrderRepository.save(order);
//        return OrderMapper.mapToOrderDto(updatedOrderObj);
//    }
}
