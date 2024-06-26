package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.OrderDetailDto;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<OrderDetailDto> createOrderDetail(@RequestBody OrderDetailDto orderDetailDto){
        OrderDetailDto savedOrderDetail = orderDetailService.createOrderDetail(orderDetailDto);
        return new ResponseEntity<>(savedOrderDetail, HttpStatus.CREATED);
    }


@GetMapping("{userId}/{orderId}")
public ResponseEntity<List<OrderDetailDto>> getAllImageByOrderId(
        @PathVariable("userId") Long userId,
        @PathVariable("orderId") Orders orderId) {
    List<OrderDetailDto> orderDetailDtos = orderDetailService.getAllOrderId(userId, orderId);
    return ResponseEntity.ok(orderDetailDtos);
}

    @GetMapping("{orderId}")
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetailByOrderId(@PathVariable("orderId") Orders orderId){
        List<OrderDetailDto> orderDetailDtos = orderDetailService.getAllOrderDetailByOrderId(orderId);
        return ResponseEntity.ok(orderDetailDtos);
    }

    @GetMapping("/top-selling-products")
    public ResponseEntity<List<Map<String, Object>>> getTopSellingProducts() {
        List<Map<String, Object>> topSellingProducts = orderDetailService.getTopSellingProducts();
        return new ResponseEntity<>(topSellingProducts, HttpStatus.OK);
    }



}
