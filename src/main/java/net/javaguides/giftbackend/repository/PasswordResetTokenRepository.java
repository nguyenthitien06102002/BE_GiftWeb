package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository  extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
