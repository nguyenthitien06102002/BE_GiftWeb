package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.repository.UsersRepository;
import net.javaguides.giftbackend.service.ForgotPasswordSvice;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Service
@AllArgsConstructor
public class ForgotPassworServiceImpl implements ForgotPasswordSvice {
    private UsersRepository usersRepository;
    private EmailSenderService emailService;

    private boolean emailExists(String email) {
        Users user = usersRepository.findByEmail(email);
        if(user != null){
            return true;
        } else {
            return false;
        }

    }


    public static String generateResetToken(String email) {
        // Sử dụng UUID để tạo một chuỗi ngẫu nhiên
        String resetToken = UUID.randomUUID().toString();
        return resetToken;
    }

    @Override
    public String forgotPassword(String email) {
        if(emailExists(email)){
            String resetToken = generateResetToken(email);

            emailService.sendResetPasswordEmail(email, resetToken);
            return "Đã gửi email để thiết lập lại mật khẩu!";
        }else {
            return "Email không tồn tại trong hệ thống!";
        }
//        Users users = usersRepository.findByEmail(email);
//        if(users == null){
//            return "Email không tồn tại trong hệ thống!";
//    }
//        String resetToken = generateResetToken(email);
//        users.setR

    }

}
