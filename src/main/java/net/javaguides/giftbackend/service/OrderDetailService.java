package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.OrderDetailDto;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Users;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {

    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);

    List<OrderDetailDto> getAllOrderId(Long userId, Orders orderId);
    List<OrderDetailDto> getAllOrderDetailByOrderId(Orders orderId);
    List<Map<String, Object>> getTopSellingProducts();

}
