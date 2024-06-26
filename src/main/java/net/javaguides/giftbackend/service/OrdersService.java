package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.OrdersDto;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.entity.*;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.Map;

public interface OrdersService {

    //tạo hóa đơn
    OrdersDto createOrder(OrdersDto ordersDto);

//    boolean doesUserHaveDiscount(Long userId, Long discountId);

    List<OrdersDto> getOrderByIdUser(Users usersId);

    OrdersDto updateStatusOrder(Long orderId, StatusOrder statusId);

    List<OrdersDto> getAllOrders();

    List<OrdersDto> getOrderByDiscountId(Discount discountId);

    List<Double> getRevenueByMonth(int year);

    Map<Integer,Double> getAnnualRevenue();

    List<Double> getOrderByMonth(int year);

    Map<String, Double> getCurrentAndPreviousMonthRevenue();

    Map<String, Long> getCurrentAndPreviousMonthOrders();

    List<Map<String, Object>> getProductsCreatedBeforeCurrentMonth();

    OrdersDto deleteOrder(Long orderId);









}
