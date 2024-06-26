package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ProductTopicDto;
import net.javaguides.giftbackend.entity.ProductTopic;

public class ProductTopicMapper {

    public static ProductTopicDto mapToProductTopicDto(ProductTopic productTopic){
        return new ProductTopicDto(
                productTopic.getId(),
                productTopic.getTopicName(),
                productTopic.getTopicImage(),
                productTopic.isActive(),
                productTopic.getCreateDate()
        );
    }
    public static ProductTopic mapToProductTopic(ProductTopicDto productTopicDto){
        return new ProductTopic(
                productTopicDto.getId(),
                productTopicDto.getTopicName(),
                productTopicDto.getTopicImage(),
                productTopicDto.isActive(),
                productTopicDto.getCreateDate()
        );
    }
}
