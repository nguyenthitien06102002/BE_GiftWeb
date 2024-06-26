package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(Orders OrderId);

    @Query(value = "SELECT od.product_id, p.product_name, pi.img_path, SUM(od.quantity) AS total_sold\n" +
            "FROM order_detail od \n" +
            "JOIN products p ON od.product_id = p.id\n" +
            "JOIN (\n" +
            "    SELECT productid, MIN(img_path) as img_path -- or some other logic to choose one image\n" +
            "    FROM img_product\n" +
            "    GROUP BY productid\n" +
            ") pi ON p.id = pi.productid\n" +
            "GROUP BY od.product_id, p.product_name, pi.img_path\n" +
            "ORDER BY total_sold DESC LIMIT 3;", nativeQuery = true)
    List<Object[]> findTopSellingProducts();

//    @Query(value = "SELECT p.id, p.product_name, pi.img_path, SUM(od.quantity) AS total_sold " +
//            "FROM order_detail od " +
//            "JOIN products p ON od.product_id = p.id " +
//            "JOIN img_product pi ON p.id = pi.productid " +
//            "GROUP BY p.id, p.product_name " +
//            "ORDER BY total_sold ASC" +
//            "LIMIT 3", nativeQuery = true)
//    List<Object[]> findTopSellingProducts();
}
