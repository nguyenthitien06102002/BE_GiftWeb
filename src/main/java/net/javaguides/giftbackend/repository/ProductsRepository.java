package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {
  //  List<Products> findTop6ByOrderByPriceMinusSalePriceDesc();
  List<Products> findTop6ByOrderByCreateDateDesc();

  @Query("SELECT p FROM Products p ORDER BY (p.price - p.salePrice) DESC LIMIT 6")
  List<Products> findTop6ByOrderByPriceMinusSalePriceDesc();


  @Query("SELECT p FROM Products p WHERE p.productName LIKE %:keyword%")
  List<Products> findByKeyword(@Param("keyword") String keyword);




}
