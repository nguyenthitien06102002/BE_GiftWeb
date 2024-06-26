package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusOrderRepository extends JpaRepository<StatusOrder, Long> {
}
