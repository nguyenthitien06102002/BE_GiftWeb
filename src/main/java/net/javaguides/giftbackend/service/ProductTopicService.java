package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.ProductTopicDto;

import java.util.List;

public interface ProductTopicService {

    //create topic
    ProductTopicDto createProductTopic(ProductTopicDto productTopicDto);

    List<ProductTopicDto> getAllTopic();

    ProductTopicDto updateActive(Long id, Boolean active);

    ProductTopicDto updateProductTopic(Long id, ProductTopicDto productTopicDto);
}
