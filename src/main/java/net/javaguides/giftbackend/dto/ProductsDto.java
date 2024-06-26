package net.javaguides.giftbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Category;
import net.javaguides.giftbackend.entity.ProductTopic;


import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
    private Long id;
    private String productName;
    private String detail;
    private double price;
    private double salePrice;
    private long stock;
    private Category categoryId;
    private ProductTopic themeId;
    private Timestamp createDate;
    private Boolean active;
    private Boolean status;


}
