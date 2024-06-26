package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.dto.LogDto;
import net.javaguides.giftbackend.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogRespository extends JpaRepository<Log, Long> {

    @Query(value = "SELECT l.path, COUNT(l.id) " +
            "FROM log l " +
            "WHERE l.event_type = 'VISIT_PAGE'  AND DATE(l.time) = CURDATE() " +
            "GROUP BY l.path", nativeQuery = true)
    List<Object[]> getDistinctUserVisitCounts();

    @Query(value = "SELECT l.path, " +
            "SUM(CASE WHEN l.user IS NULL THEN 1 ELSE 0 END) AS nullUserCount, " +
            "SUM(CASE WHEN l.user IS NOT NULL THEN 1 ELSE 0 END) AS nonNullUserCount " +
            "FROM log l " +
            "WHERE l.event_type = 'VISIT_PAGE' " +
            "AND DATE(l.time) = CURDATE() " +
            "GROUP BY l.path", nativeQuery = true)
    List<Object[]> getVisitCounts();

    @Query(value = "SELECT l.path, " +
            "SUM(CASE WHEN DATE(l.time) = CURDATE() THEN 1 ELSE 0 END) AS todayCount, " +
            "SUM(CASE WHEN DATE(l.time) = CURDATE() - INTERVAL 1 DAY THEN 1 ELSE 0 END) AS yesterdayCount, " +
            "SUM(CASE WHEN DATE(l.time) = CURDATE() AND l.user IS NULL THEN 1 ELSE 0 END) AS todayNullUserCount, " +
            "SUM(CASE WHEN DATE(l.time) = CURDATE() AND l.user IS NOT NULL THEN 1 ELSE 0 END) AS todayNonNullUserCount " +
            "FROM log l " +
            "WHERE l.event_type = 'VISIT_PAGE' " +
            "GROUP BY l.path", nativeQuery = true)
    List<Object[]> getVisitCountsComparison();

}
