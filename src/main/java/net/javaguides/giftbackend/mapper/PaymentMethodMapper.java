package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.PaymentMethodDto;
import net.javaguides.giftbackend.entity.PaymentMethod;

public class PaymentMethodMapper {
    public static PaymentMethodDto mapToPaymentMethodDto(PaymentMethod paymentMethod){
        return new PaymentMethodDto(
                paymentMethod.getId(),
                paymentMethod.getPaymentName()
        );
    }
    public static  PaymentMethod mapToPaymentMethod(PaymentMethodDto paymentMethodDto){
        return new PaymentMethod(
                paymentMethodDto.getId(),
                paymentMethodDto.getPaymentName()
        );
    }
}
