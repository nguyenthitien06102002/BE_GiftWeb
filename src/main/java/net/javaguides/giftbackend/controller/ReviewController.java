package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ReviewDto;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        String ipAddress = getClientIpAddress();
        reviewDto.setIp(ipAddress);
        ReviewDto savedReview = reviewService.createReview(reviewDto);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable("productId") Products productId) {
        List<ReviewDto> reviewDtos = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviewDtos);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviewDtos = reviewService.getAllReviews();
        return ResponseEntity.ok(reviewDtos);
    }

@GetMapping("get/{reviewId}")
public ResponseEntity<ReviewDto> getReviewById(@PathVariable("reviewId") Long reviewId) {
    ReviewDto reviewDto = reviewService.getReviewById(reviewId);
    return ResponseEntity.ok(reviewDto);
}


    private String getClientIpAddress() {
        try {
            Socket socket = new Socket("google.com", 80); // Kết nối tạm thời đến một host bất kỳ
            InetAddress address = socket.getLocalAddress(); // Lấy địa chỉ IP của client từ socket
            return address.getHostAddress();
        } catch (Exception e) {
            return "Unknown"; // Trả về "Unknown" nếu không thể lấy được địa chỉ IP
        }
    }
}
