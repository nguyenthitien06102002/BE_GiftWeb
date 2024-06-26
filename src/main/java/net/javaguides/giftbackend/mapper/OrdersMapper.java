package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.OrdersDto;
import net.javaguides.giftbackend.entity.Orders;

public class OrdersMapper {

    public static OrdersDto mapToOrdersDto(Orders orders){
        return new OrdersDto(
                orders.getId(),
                orders.getDiscountId(),
                orders.getUserId(),
                orders.getPaymentId(),
                orders.getIp(),
                orders.getCreateTime(),
                orders.getOrderName(),
                orders.getPhone(),
                orders.getEmail(),
                orders.getAddress(),
                orders.getProvinceId(),
                orders.getDistrictId(),
                orders.getNote(),
                orders.getTotalPrice(),
                orders.getTransport(),
                orders.getStatus()

        );
    }
    public static Orders mapToOrders(OrdersDto ordersDto){
        return new Orders(
                ordersDto.getId(),
                ordersDto.getDiscountId(),
                ordersDto.getUserId(),
                ordersDto.getPaymentId(),
                ordersDto.getIp(),
                ordersDto.getCreateTime(),
                ordersDto.getOrderName(),
                ordersDto.getPhone(),
                ordersDto.getEmail(),
                ordersDto.getAddress(),
                ordersDto.getProvinceId(),
                ordersDto.getDistrictId(),
                ordersDto.getNote(),
                ordersDto.getTotalPrice(),
                ordersDto.getTransport(),
                ordersDto.getStatus()


        );
    }
}
