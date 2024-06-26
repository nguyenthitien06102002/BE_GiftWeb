package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.PasswordResetToken;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.request.ChangePasswordRequest;
import net.javaguides.giftbackend.service.PasswordResetTokenService;
import net.javaguides.giftbackend.service.UsersService;
import net.javaguides.giftbackend.service.impl.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/passwordReset")
public class PasswordResetController {
    @Autowired
    private PasswordResetTokenService tokenService;

    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private UsersService usersService;



    @PostMapping("/request/{email}")
    public ResponseEntity<?> requestPasswordReset(@PathVariable String email){
        Users users = usersService.findByEmail(email);
        if(users == null){
            return ResponseEntity.badRequest().body("No user found with this email");
        }
        PasswordResetToken token = tokenService.ceatePasswordResetToken(users);
        emailService.sendResetPasswordEmail(email, token.getToken());
        return ResponseEntity.ok("Email sent");
    }

//    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String newPassword){
    @PutMapping("/reset/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable("token") String token, @RequestParam(value = "newPassword")String newPassword){
        PasswordResetToken resetToken = tokenService.findByToken(token);
        if(resetToken == null || resetToken.isExpired()){
            return ResponseEntity.badRequest().body("Invalid token");
        }
        Users users = resetToken.getUserId();
        usersService.updatePassword(users, newPassword);
        tokenService.deleteToken(resetToken);
        return ResponseEntity.ok("Password updated successfully");
    }




}
