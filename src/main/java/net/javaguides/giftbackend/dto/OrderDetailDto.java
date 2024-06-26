package net.javaguides.giftbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Products;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderDetailDto {
    private Long orderDetailId;
    private Orders orderId;
    private Products productId;
    private double price;
    private Long quantity;
    private double total;
}
