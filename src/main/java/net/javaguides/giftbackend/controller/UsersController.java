package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.LoginRequest;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.request.ChangePasswordRequest;
import net.javaguides.giftbackend.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    //kiểm tra email có tồn t
    @GetMapping("/check-socialLoginId/{socialLoginId}")
    public ResponseEntity<UsersDto> checkEmailExists(@PathVariable String socialLoginId) {
        UsersDto usersDto = usersService.checkSocialLoginIdExists(socialLoginId);
        return ResponseEntity.ok(usersDto);
    }

    //SingUp User
    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {
        String ipAddress = getClientIpAddress(); // Lấy địa chỉ IP của client

        usersDto.setIP(ipAddress); // Thiết lập địa chỉ IP trong UsersDto

        // Mã hóa mật khẩu của người dùng bằng SHA-256
        String hashedPassword = sha256(usersDto.getPassword());
        usersDto.setPassword(hashedPassword);

        UsersDto savedUsers = usersService.createUser(usersDto);
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }
//lay dia chi IP
    private String getClientIpAddress() {
        try {
            Socket socket = new Socket("google.com", 80); // Kết nối tạm thời đến một host bất kỳ
            InetAddress address = socket.getLocalAddress(); // Lấy địa chỉ IP của client từ socket
            return address.getHostAddress();
        } catch (Exception e) {
            return "Unknown"; // Trả về "Unknown" nếu không thể lấy được địa chỉ IP
        }
    }

    //build get user rest API
    @GetMapping("{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable("id") Long userId){
        UsersDto usersDto = usersService.getUserByID(userId);
        return ResponseEntity.ok(usersDto);
    }

    //Buil Get All Employees Rest API
    @GetMapping
    public  ResponseEntity<List<UsersDto>> getUsers(){
        List<UsersDto> usersDtos = usersService.getAllUsers();
        return  ResponseEntity.ok(usersDtos);
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Lấy thông tin đăng nhập từ request
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        // Mã hóa mật khẩu từ yêu cầu đăng nhập
        String hashedPassword = sha256(password); // Hàm sha256 sẽ được triển khai ở phía server
        // Gọi service để kiểm tra đăng nhập
        UsersDto userDto = usersService.getUserByEmailAndPassWord(email, hashedPassword);
        if (userDto != null) {
            // Nếu người dùng tồn tại, trả về thông tin người dùng
            return ResponseEntity.ok(userDto);
        } else {
            // Nếu không tìm thấy người dùng, trả về lỗi Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    //login gg
    @PostMapping("/login-google")
    public ResponseEntity<?> loginGG (@RequestBody LoginRequest loginRequest){
        // Lấy thông tin đăng nhập từ request
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UsersDto userDto = usersService.getUserByEmailAndPassWord(email, password);

        if (userDto != null) {
            // Nếu người dùng tồn tại, trả về thông tin người dùng
            return ResponseEntity.ok(userDto);
        } else {
            // Nếu không tìm thấy người dùng, trả về lỗi Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }


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

    //update
    @PutMapping("update/{id}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable("id") Long userId, @RequestBody UsersDto updateUser){
        UsersDto usersDto = usersService.updateUser(userId, updateUser);
        return ResponseEntity.ok(usersDto);
    }

    @PutMapping("updateStatus/{id}")
    public ResponseEntity<UsersDto> updateStatus(@PathVariable("id") Long userId, @RequestBody UsersDto updateUser){
        UsersDto usersDto = usersService.updateStatus(userId, updateUser);
        return ResponseEntity.ok(usersDto);
    }
    @PutMapping("/password/{id}")
    public ResponseEntity<UsersDto> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordRequest request) {
        UsersDto usersDto = usersService.changePassword(id, request);
        return ResponseEntity.ok(usersDto);
    }

    @PutMapping("deleteUser/{id}")
    public ResponseEntity<UsersDto> deleteProduct(@PathVariable("id") Long userId){
        UsersDto usersDto = usersService.deleteUser(userId);
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/user-count/current-and-previous-month")
    public ResponseEntity<Map<String, Long>> getCurrentAndPreviousMonthOrders() {
        Map<String, Long> orders = usersService.getCurrentAndPreviousMontUsers();
        return ResponseEntity.ok(orders);
    }

}
