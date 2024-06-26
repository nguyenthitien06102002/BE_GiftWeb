package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndPassword(String email, String password);

    Users findBySocialLoginId(String socialLoginId);

    Users findByEmail(String email);

    @Query(value = "SELECT " +
            "COUNT(CASE WHEN YEAR(create_time) = YEAR(CURRENT_DATE) AND MONTH(create_time) = MONTH(CURRENT_DATE) THEN 1 END) AS currentMonthOrders, " +
            "COUNT(CASE WHEN YEAR(create_time) = YEAR(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) AND MONTH(create_time) = MONTH(DATE_SUB(CURRENT_DATE, INTERVAL 1 MONTH)) THEN 1 END) AS previousMonthOrders " +
            "FROM users", nativeQuery = true)
    List<Object[]> findCurrentAndPreviousMonthUsers();
}
