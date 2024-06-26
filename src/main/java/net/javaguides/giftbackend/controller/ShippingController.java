package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.Address;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/shippingFee")
public class ShippingController {

    @PostMapping
    public int calculateShippingFee(@RequestBody Address address) {
        // Thực hiện logic tính phí vận chuyển dựa trên tỉnh/thành phố và quận/huyện
        // Trong ví dụ này, giả sử có một bảng giá cố định cho từng tỉnh/thành phố và quận/huyện
        // Bạn có thể sử dụng cơ sở dữ liệu hoặc một bộ quy tắc để ánh xạ giữa địa chỉ và phí vận chuyển
        int shippingFee = 0;
        int i = 25000;
        int y = 30000;

        if (address.getProvinceID() == 1) {
            if (address.getDistrictID() == 1) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 2) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 3) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 19) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 21) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 4) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 5) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 6) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 7) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else  if (address.getDistrictID() == 8) {
                shippingFee = i; // Phí vận chuyển cho quận Ba Đình ở Hà Nội
            } else {
                shippingFee = y; // Phí vận chuyển cho các quận khác ở Hà Nội
            }
        } else if (address.getProvinceID() == 79) {
            shippingFee = 21000; // Phí vận chuyển cho TP.HCM
        } else {
            shippingFee = 35000; // Phí vận chuyển mặc định cho các tỉnh/thành phố khác
        }

        return shippingFee;
    }

}
