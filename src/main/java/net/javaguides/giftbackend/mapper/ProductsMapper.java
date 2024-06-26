package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.entity.Products;

public class ProductsMapper {

    public static ProductsDto maptoProductsDto(Products products){
        return new ProductsDto(
                products.getId(),
                products.getProductName(),
                products.getDetail(),
                products.getPrice(),
                products.getSalePrice(),
                products.getStock(),
                products.getCategoryId(),
                products.getThemeId(),
                products.getCreateDate(),
                products.getActive(),
                products.getStatus()

        );
    }
    public static Products mapToProduct(ProductsDto productsDto){
        return new Products(
                productsDto.getId(),
                productsDto.getProductName(),
                productsDto.getDetail(),
                productsDto.getPrice(),
                productsDto.getSalePrice(),
                productsDto.getStock(),
                productsDto.getCategoryId(),
                productsDto.getThemeId(),
                productsDto.getCreateDate(),
                productsDto.getActive(),
                productsDto.getStatus()
        );
    }
}
