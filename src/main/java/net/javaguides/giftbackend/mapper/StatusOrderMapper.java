package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.StatusOrderDto;
import net.javaguides.giftbackend.entity.StatusOrder;

public class StatusOrderMapper {
    public static StatusOrderDto mapToStatusOrderDto(StatusOrder statusOrder) {
        return new StatusOrderDto(
                statusOrder.getId(),
                statusOrder.getStatusName());
    }
    public static StatusOrder mapToStatusOrder(StatusOrderDto statusOrderDto) {
        return new StatusOrder(
                statusOrderDto.getId(),
                statusOrderDto.getStatusName());
    }
}
