package net.javaguides.giftbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {
    private Long id;
    private Discount discountId;
    private Users userId;
    private PaymentMethod paymentId;
    private String ip;
    private Timestamp createTime;
    private String orderName;
    private String phone;
    private String email;
    private String address;
    private Provinces provinceId;
    private Districts districtId;
    private String note;
    private double totalPrice;
    private double transport;
//    private Long discountId;
    private StatusOrder status;
//    private List<OrderDetail> orderDetails;
}
