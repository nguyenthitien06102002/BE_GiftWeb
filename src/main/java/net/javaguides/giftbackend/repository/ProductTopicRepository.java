package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.ProductTopic;
import net.javaguides.giftbackend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTopicRepository extends  JpaRepository<ProductTopic, Long>{
}
