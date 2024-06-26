package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.OrdersDto;
import net.javaguides.giftbackend.entity.Discount;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.StatusOrder;
import net.javaguides.giftbackend.entity.Users;
import net.javaguides.giftbackend.service.OrdersService;
import net.javaguides.giftbackend.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<OrdersDto> createOrders(@RequestBody OrdersDto ordersDto){
        String ipAddress = getClientIpAddress();
        ordersDto.setIp(ipAddress);
        OrdersDto savedOrders = ordersService.createOrder(ordersDto);
        return new ResponseEntity<>(savedOrders, HttpStatus.CREATED);
    }


    @GetMapping("{userId}")
    public ResponseEntity<List<OrdersDto>> getOrderByUserId(@PathVariable("userId") Users userId){
        List<OrdersDto> ordersDtos = ordersService.getOrderByIdUser(userId);
        return ResponseEntity.ok(ordersDtos);
    }

    @PutMapping("updateStatus/{orderId}")
    public ResponseEntity<?> updateStatusOrder(@PathVariable("orderId") Long orderId, @RequestBody StatusOrder statusId){
        ordersService.updateStatusOrder(orderId, statusId);
        return ResponseEntity.ok("Status updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAllOrders(){
        List<OrdersDto> ordersDtos = ordersService.getAllOrders();
        return ResponseEntity.ok(ordersDtos);
    }

    @GetMapping("discount/{discountId}")
    public ResponseEntity<List<OrdersDto>> getOrderByDiscountId(@PathVariable("discountId") Discount discountId){
        List<OrdersDto> ordersDtos = ordersService.getOrderByDiscountId(discountId);
        return ResponseEntity.ok(ordersDtos);
    }

    @GetMapping("revenue/{year}")
    public ResponseEntity<List<Double>> getRevenueByMonth(@PathVariable("year") int year){
        List<Double> revenue = ordersService.getRevenueByMonth(year);
        return ResponseEntity.ok(revenue);
    }
    @GetMapping("total-order/{year}")
    public ResponseEntity<List<Double>> getOrderByMonth(@PathVariable("year") int year){
        List<Double> revenue = ordersService.getOrderByMonth(year);
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("annualRevenue")
    public Map<Integer, Double> getAnnualRevenue(){
        return ordersService.getAnnualRevenue();
    }

    @GetMapping("/revenue/current-and-previous-month")
    public ResponseEntity<Map<String, Double>> getCurrentAndPreviousMonthRevenue() {
        Map<String, Double> revenue = ordersService.getCurrentAndPreviousMonthRevenue();
        return ResponseEntity.ok(revenue);
    }

    @GetMapping("/order-count/current-and-previous-month")
    public ResponseEntity<Map<String, Long>> getCurrentAndPreviousMonthOrders() {
        Map<String, Long> orders = ordersService.getCurrentAndPreviousMonthOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("product/created-before-current-month")
    public List<Map<String, Object>> getProductsCreatedBeforeCurrentMonth() {
        return ordersService.getProductsCreatedBeforeCurrentMonth();
    }

    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") Long orderId){
        ordersService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }




    //lay dia chi IP
    private String getClientIpAddress() {
        try {
            Socket socket = new Socket("google.com", 80);
            InetAddress address = socket.getLocalAddress();
            return address.getHostAddress();
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
