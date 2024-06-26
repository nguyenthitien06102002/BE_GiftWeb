package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.DiscountDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.Users;

import java.util.List;

public interface DiscountService {

    //create discount
    DiscountDto createDiscount(DiscountDto discountDto);

    DiscountDto applyDiscount(Users userId, String code);
    List<DiscountDto> getAllDiscounts();

    DiscountDto deleteDiscount(Long discountId);

    DiscountDto updateDiscount(Long discountId, DiscountDto discountDto);
}
