package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.entity.MailStructure;
import net.javaguides.giftbackend.service.ForgotPasswordSvice;
import net.javaguides.giftbackend.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private EmailSenderService mailSender;

    @Autowired
    private ForgotPasswordSvice forgotPasswordSvice;

    @PostMapping("send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure) {
        mailSender.sendEmail(mail, mailStructure);
        return "Mail sent successfully";


    }

    @PostMapping("send2/{email}")
    public String forgotPassword(@PathVariable String email) {
//        forgotPasswordSvice.forgotPassword(email);
        return  forgotPasswordSvice.forgotPassword(email);
    }



}
