package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
