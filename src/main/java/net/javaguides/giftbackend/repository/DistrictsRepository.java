package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.Districts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictsRepository extends JpaRepository<Districts, Long> {
    List<Districts> findByProvinceID(Long provinceID);
}
