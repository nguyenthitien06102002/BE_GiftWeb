package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
   Orders findByUserIdAndDiscountId(Users userId, Discount discountId);
   List<Orders> findByUserId(Users userId);

   List<Orders> findByDiscountId(Discount discountId);

   List<Orders> findByCreateTimeBetween(Timestamp start, Timestamp end);


   @Query("SELECT YEAR(o.createTime) AS year, SUM(o.totalPrice) AS totalRevenue " +
           "FROM Orders o GROUP BY YEAR(o.createTime) ORDER BY YEAR(o.createTime)")
   List<Object[]> findAnnualRevenue();

   @Query(value = "SELECT " +
           "SUM(CASE WHEN YEAR(create_time) = YEAR(CURRENT_DATE) AND MONTH(create_time) = MONTH(CURRENT_DATE) THEN total_price ELSE 0 END) AS currentMonthRevenue, " +
           "SUM(CASE WHEN YEAR(create_time) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(create_time) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) THEN total_price ELSE 0 END) AS previousMonthRevenue " +
           "FROM orders", nativeQuery = true)
   List<Object[]> findCurrentAndPreviousMonthRevenue();


   @Query(value = "SELECT " +
           "COUNT(CASE WHEN YEAR(create_time) = YEAR(CURRENT_DATE) AND MONTH(create_time) = MONTH(CURRENT_DATE) THEN 1 END) AS currentMonthOrders, " +
           "COUNT(CASE WHEN YEAR(create_time) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(create_time) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) THEN 1 END) AS previousMonthOrders " +
           "FROM orders", nativeQuery = true)
   List<Object[]> findCurrentAndPreviousMonthOrders();

   @Query(value = "SELECT " +
           "p.id AS productId, " +
           "p.product_name AS productName, " +
           "(SELECT pi.img_path FROM img_product pi WHERE pi.productid = p.id LIMIT 1) AS imgPath, " +
           "COALESCE(SUM(od.quantity), 0) AS totalSold " +
           "FROM " +
           "products p " +
           "LEFT JOIN " +
           "order_detail od ON p.id = od.product_id " +
           "WHERE " +
           "p.status != 0 AND " +
           "p.create_date < DATE_FORMAT(CURRENT_DATE, '%Y-%m-01') " +
           "GROUP BY " +
           "p.id, p.product_name " +
           "HAVING " +
           "totalSold = 0 OR totalSold = 1 " +
           "ORDER BY " +
           "totalSold ASC " +
           "LIMIT 3", nativeQuery = true)
   List<Object[]> findProductsCreatedBeforeCurrentMonth();


}
