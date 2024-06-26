package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.mapper.ProductsMapper;
import net.javaguides.giftbackend.mapper.UsersMapper;
import net.javaguides.giftbackend.repository.UsersRepository;
import net.javaguides.giftbackend.request.ChangePasswordRequest;
import net.javaguides.giftbackend.service.UsersService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceIpml implements UsersService {
    private UsersRepository usersRepository;

    @Override
    public UsersDto createUser(UsersDto usersDto) {
        Users users = UsersMapper.mapToUsers(usersDto);
        Users saveUsers = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(saveUsers);
    }
//getUserBy ID
    @Override
    public UsersDto getUserByID(Long userID) {
      Users users =  usersRepository.findById(userID)
                .orElseThrow(() ->
                new ResourceNotFoundException("user is not exist with given id: " + userID));

        return UsersMapper.mapToUsersDto(users);
    }
//getUserByEmailAndPassWord
    @Override
    public UsersDto getUserByEmailAndPassWord(String email, String password) {
        Users users = usersRepository.findByEmailAndPassword(email, password);
        // Kiểm tra xem người dùng có tồn tại không
        if (users == null) {
            throw new ResourceNotFoundException("Image is not exist with given  : " + email + password);
        }
        return UsersMapper.mapToUsersDto(users);

    }



    @Override
    public List<UsersDto> getAllUsers() {
       List<Users> users = usersRepository.findAll();
        return users.stream().map((user) -> UsersMapper.mapToUsersDto(user))
                .collect(Collectors.toList());
    }

  @Override
  public UsersDto checkSocialLoginIdExists(String socialLoginId) {
      Users users = usersRepository.findBySocialLoginId(socialLoginId);
      if (users != null) { // Nếu tìm thấy người dùng với email đã cho
          return UsersMapper.mapToUsersDto(users); // Trả về DTO của người dùng
      } else { // Nếu không tìm thấy người dùng với email đã cho
          return null;
      }
  }

    @Override
    public UsersDto updateUser(long userId, UsersDto updateUser) {

      Users users =  usersRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
      users.setUserName(updateUser.getUserName());
      users.setEmail(updateUser.getEmail());
      users.setPhoneNumber(updateUser.getPhoneNumber());

        Users updatedUserObj = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(updatedUserObj);
    }

    @Override
    public UsersDto updateStatus(long userId, UsersDto updateUser) {
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
        users.setStatus(updateUser.getStatus());
        Users updatedUserObj = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(updatedUserObj);
    }

//

    @Override
    public UsersDto changePassword(Long id, ChangePasswordRequest changePasswordRequest) {

        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        String currentPassword = changePasswordRequest.getCurrentPassword();
        String newPassword = changePasswordRequest.getNewPassword();

        String hashedCurrentPassword = sha256(currentPassword);

        if (!users.getPassword().equals(hashedCurrentPassword)) {
            throw new RuntimeException("Mật khẩu hiện tại không đúng");
        }

        String hashedNewPassword = sha256(newPassword);
        users.setPassword(hashedNewPassword);

        Users updatedUser = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(updatedUser);
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public UsersDto updatePassword(Users users, String newPassword) {
//        Long id = users.getId();
//        Users users1 = usersRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        String hashedNewPassword = sha256(newPassword);
        users.setPassword(hashedNewPassword);
        Users updatedUserPass = usersRepository.save(users);

        return UsersMapper.mapToUsersDto(updatedUserPass);
    }

    @Override
    public UsersDto deleteUser(Long userId) {
        long status = 3L;
        Users users = usersRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + userId));
        users.setStatus(status);
        Users updateStatus = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(updateStatus);
    }

    @Override
    public Map<String, Long> getCurrentAndPreviousMontUsers() {
        List<Object[]> results = usersRepository.findCurrentAndPreviousMonthUsers();
        Map<String, Long> revenueMap = new HashMap<>();
        if (!results.isEmpty()) {
            Object[] result = results.get(0);
            Long currentMonthUser = ((Number) result[0]).longValue();
            Long previousMonthUser = ((Number) result[1]).longValue();
            revenueMap.put("currentMonthUser", currentMonthUser);
            revenueMap.put("previousMonthUser", previousMonthUser);
        }
        return revenueMap;
    }


    // Hàm mã hóa SHA-256
    private String sha256(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Mã hóa mật khẩu
            byte[] encodedHash = digest.digest(password.getBytes());

            // Chuyển đổi byte array thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Xử lý lỗi nếu thuật toán không tồn tại
            e.printStackTrace();
            return null;
        }
    }


}
