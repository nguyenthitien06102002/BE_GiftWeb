package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Products productId);
}
