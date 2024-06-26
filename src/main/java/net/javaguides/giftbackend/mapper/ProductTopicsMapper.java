package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ProductTopicsDto;
import net.javaguides.giftbackend.entity.ProductTopic;
import net.javaguides.giftbackend.entity.ProductTopics;

public class ProductTopicsMapper {
    public static ProductTopicsDto mapToProductTopicsDto(ProductTopics productTopics) {
        return new ProductTopicsDto(
                productTopics.getId(),
                productTopics.getProduct(),
                productTopics.getTopic());
    }

    public static ProductTopics mapToProductTopics(ProductTopicsDto productTopicsDto) {
        return new ProductTopics(
                productTopicsDto.getId(),
                productTopicsDto.getProduct(),
                productTopicsDto.getTopic());
    }
}
