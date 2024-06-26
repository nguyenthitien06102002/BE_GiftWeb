package net.javaguides.giftbackend.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.ProductTopic;
import net.javaguides.giftbackend.entity.Products;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTopicsDto {
    private Long id;
    private Products product;
    private ProductTopic topic;
}
