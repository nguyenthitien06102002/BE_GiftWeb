package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.ImgProduct;
import net.javaguides.giftbackend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImgProductRepository extends JpaRepository<ImgProduct, Long> {
   ImgProduct findFirstByProductID(Products productID);

    List<ImgProduct> findByProductID(Products productID);
}
