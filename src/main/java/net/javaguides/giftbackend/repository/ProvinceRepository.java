package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.Districts;
import net.javaguides.giftbackend.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Provinces, Long> {

}
