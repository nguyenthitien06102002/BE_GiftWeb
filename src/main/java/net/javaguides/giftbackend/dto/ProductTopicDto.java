package net.javaguides.giftbackend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Products;


import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductTopicDto {
    private Long id;

    private String topicName;

    private String topicImage;

    private boolean active;

    private Timestamp createDate;

}
