package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.DiscountDto;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    private final DiscountService discountService;


    @PostMapping
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody DiscountDto discountDto){
        String ipAddress = getClientIpAddress();
        discountDto.setIP(ipAddress);

        DiscountDto saved = discountService.createDiscount(discountDto);
        return  new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiscountDto>> getAllDiscounts(){
        List<DiscountDto> discountDtos = discountService.getAllDiscounts();
        return ResponseEntity.ok(discountDtos);
    }

    @PutMapping("delete/{discountId}")
   public ResponseEntity<DiscountDto> deleteDiscount(@PathVariable("discountId") Long discountId){
        DiscountDto discountDto = discountService.deleteDiscount(discountId);
        return ResponseEntity.ok(discountDto);
    }

    @PutMapping("update/{discountId}")
    public ResponseEntity<DiscountDto> updateDiscount(@PathVariable("discountId") Long discountId, @RequestBody DiscountDto discountDto){
        DiscountDto updatedDiscount = discountService.updateDiscount(discountId, discountDto);
        return ResponseEntity.ok(updatedDiscount);
    }


    @GetMapping("/{userId}/{code}")
    public ResponseEntity<?> applyDiscount(@PathVariable Users userId, @PathVariable String code) {
        try {
            DiscountDto discountDto = discountService.applyDiscount(userId, code);
            return ResponseEntity.ok(discountDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
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




}
