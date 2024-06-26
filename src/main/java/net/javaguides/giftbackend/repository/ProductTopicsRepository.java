package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.ProductTopics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTopicsRepository extends JpaRepository<ProductTopics, Long> {
}
