package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ProductTopicDto;
import net.javaguides.giftbackend.entity.ProductTopic;
import net.javaguides.giftbackend.mapper.ProductTopicMapper;
import net.javaguides.giftbackend.repository.ProductTopicRepository;
import net.javaguides.giftbackend.service.ProductTopicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductTopicServiceImpl implements ProductTopicService {
    private ProductTopicRepository productTopicRepository;
    @Override
    public ProductTopicDto createProductTopic(ProductTopicDto productTopicDto) {
        ProductTopic productTopic = ProductTopicMapper.mapToProductTopic(productTopicDto);
        ProductTopic saveProductTopic = productTopicRepository.save(productTopic);
        return ProductTopicMapper.mapToProductTopicDto(saveProductTopic);
    }

    @Override
    public List<ProductTopicDto> getAllTopic() {
        List<ProductTopic> productTopics = productTopicRepository.findAll();
        return productTopics.stream().map((productTopic) -> ProductTopicMapper.mapToProductTopicDto(productTopic))
                .collect(Collectors.toList());
    }

    @Override
    public ProductTopicDto updateActive(Long id, Boolean active) {
        ProductTopic productTopic = productTopicRepository.findById(id).orElse(null);
        if(productTopic != null){
            productTopic.setActive(active);
            ProductTopic saveProductTopic = productTopicRepository.save(productTopic);
            return ProductTopicMapper.mapToProductTopicDto(saveProductTopic);
        }
        return null;
    }

    @Override
    public ProductTopicDto updateProductTopic(Long id, ProductTopicDto productTopicDto) {
        ProductTopic productTopic = productTopicRepository.findById(id).orElse(null);
        if(productTopic != null){
            productTopic.setTopicName(productTopicDto.getTopicName());
            ProductTopic saveProductTopic = productTopicRepository.save(productTopic);
            return ProductTopicMapper.mapToProductTopicDto(saveProductTopic);
        }
        return null;
    }
}
