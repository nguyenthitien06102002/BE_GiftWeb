package net.javaguides.giftbackend.service.impl;

import net.javaguides.giftbackend.entity.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String formMail;





    public void sendEmail(String mail, MailStructure mailStructure) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(formMail);
        message.setSubject(mailStructure.getSubject());
        message.setText(mailStructure.getMessage());
        message.setTo(mail);

        mailSender.send(message);


    }

    public void sendResetPasswordEmail(String mail, String resetToken) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(formMail);
//        message.setSubject(mailStructure.getSubject());
//        message.setText(mailStructure.getMessage());
//        message.setTo(mail);
//
//        mailSender.send(message);

        // Tạo nội dung email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(formMail);
        message.setTo(mail);
        message.setSubject("Thay đổi mật khẩu");
        message.setText( "FoxBox: Hoàn tất yêu cầu quên mật khẩu tại  http://localhost:3000/reset-password?token=" + resetToken + " " + "(Link có hiệu lực trong 10 phút)");
        // Gửi email
        mailSender.send(message);
    }



}