package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.PasswordResetToken;
import net.javaguides.giftbackend.entity.Users;

public interface PasswordResetTokenService {
    PasswordResetToken ceatePasswordResetToken(Users users);

    PasswordResetToken findByToken(String token);

    void deleteToken(PasswordResetToken token);
}
