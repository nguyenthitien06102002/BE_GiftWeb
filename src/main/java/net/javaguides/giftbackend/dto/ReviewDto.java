package net.javaguides.giftbackend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Orders;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Users;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private Users userId;
    private Products productId;
    private Orders ordersId;
    private long rating;
    private String content;
    private Timestamp createDate;
    private long status;
    private String ip;
}
