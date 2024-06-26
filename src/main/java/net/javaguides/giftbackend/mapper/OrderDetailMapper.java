package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.OrderDetailDto;
import net.javaguides.giftbackend.entity.OrderDetail;

public class OrderDetailMapper {

    public  static OrderDetailDto mapToOrderDetailDto(OrderDetail orderDetai){
        return  new OrderDetailDto(
                orderDetai.getOrderDetailId(),
                orderDetai.getOrderId(),
                orderDetai.getProductId(),
                orderDetai.getPrice(),
                orderDetai.getQuantity(),
                orderDetai.getTotal()
        );
    }

    public static OrderDetail mapToOrderDetai(OrderDetailDto orderDetailDto){
        return new OrderDetail(
                orderDetailDto.getOrderDetailId(),
                orderDetailDto.getOrderId(),
                orderDetailDto.getProductId(),
                orderDetailDto.getPrice(),
                orderDetailDto.getQuantity(),
                orderDetailDto.getTotal()
        );
    }


}
