package net.javaguides.giftbackend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DiscountDto {
    private Long id;
    private String code;
    private double discountPercentage;
    private boolean active;
    private Timestamp creationDate;
    private Timestamp startDate;
    private Timestamp endStart;
    private String IP;
}
