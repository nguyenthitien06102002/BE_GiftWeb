package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.request.ChangePasswordRequest;

import java.util.List;
import java.util.Map;

public interface UsersService {
    //đăng ký
    UsersDto createUser(UsersDto usersDto);
    //get
    UsersDto getUserByID(Long userID);
    //login
    UsersDto getUserByEmailAndPassWord(String email, String password);
    //getAllUser
    List<UsersDto> getAllUsers();

//    boolean checkEmailExists(String email);

    UsersDto checkSocialLoginIdExists(String email);

    //update
    UsersDto updateUser(long userId, UsersDto updateUser);

    UsersDto updateStatus(long userId, UsersDto updateUser);

    //changePassword
    UsersDto changePassword(Long id, ChangePasswordRequest request);

    Users findByEmail(String email);

    UsersDto updatePassword (Users users, String newPassword);

    UsersDto deleteUser(Long userId);
    Map<String, Long> getCurrentAndPreviousMontUsers();


}
