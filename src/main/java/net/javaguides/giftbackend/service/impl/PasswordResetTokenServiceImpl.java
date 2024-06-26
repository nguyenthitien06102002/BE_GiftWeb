package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.entity.PasswordResetToken;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.repository.PasswordResetTokenRepository;
import net.javaguides.giftbackend.repository.UsersRepository;
import net.javaguides.giftbackend.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    private UsersRepository usersRepository;
    @Override
    public PasswordResetToken ceatePasswordResetToken(Users users) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUserId(users);
        passwordResetToken.setExpiryDate(PasswordResetTokenUtil.calculateExpiryDate());
        return tokenRepository.save(passwordResetToken);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public class PasswordResetTokenUtil {
        public static Timestamp calculateExpiryDate() {
            int expiryTimeInMinutes = 10;
            return Timestamp.valueOf(LocalDateTime.now().plusMinutes(expiryTimeInMinutes));
        }
    }

    // Phương thức để xóa token dựa trên đối tượng token

    @Override
    public void deleteToken(PasswordResetToken token) {
        tokenRepository.delete(token);
    }

}
