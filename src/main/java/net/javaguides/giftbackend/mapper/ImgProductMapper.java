package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.entity.ImgProduct;

public class ImgProductMapper {

    public static ImgProductDto mapToImgProductDto(ImgProduct imgProduct){
        return new ImgProductDto(
                imgProduct.getId(),
                imgProduct.getProductID(),
                imgProduct.getCaption(),
                imgProduct.getImgPath()
        );
    }
    public static ImgProduct mapToImgProduct(ImgProductDto imgProductDto){
        return new ImgProduct(
                imgProductDto.getId(),
                imgProductDto.getProductID(),
                imgProductDto.getCaption(),
                imgProductDto.getImgPath()
        );
    }
}
