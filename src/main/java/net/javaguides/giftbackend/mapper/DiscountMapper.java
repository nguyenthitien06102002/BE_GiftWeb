package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.DiscountDto;
import net.javaguides.giftbackend.entity.Discount;

public class DiscountMapper {

    public static DiscountDto mapToDiscountDto(Discount discount){
        return  new DiscountDto(
                discount.getId(),
                discount.getCode(),
                discount.getDiscountPercentage(),
                discount.isActive(),
                discount.getCreationDate(),
                discount.getStartDate(),
                discount.getEndStart(),
                discount.getIP()
        );
    }
    public static Discount mapToDiscount(DiscountDto discountDto){
        return new Discount(
                discountDto.getId(),
                discountDto.getCode(),
                discountDto.getDiscountPercentage(),
                discountDto.isActive(),
                discountDto.getCreationDate(),
                discountDto.getStartDate(),
                discountDto.getEndStart(),
                discountDto.getIP()

        );
    }
}
