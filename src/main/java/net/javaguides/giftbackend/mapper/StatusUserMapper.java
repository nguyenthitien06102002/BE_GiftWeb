package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.StatusUserDto;
import net.javaguides.giftbackend.entity.StatusUser;

public class StatusUserMapper {
    public static StatusUserDto mapToStatusUserDto(StatusUser statusUser) {
        return new StatusUserDto(
                statusUser.getId()
                , statusUser.getName());
    }

    public static StatusUser mapToStatusUser(StatusUserDto statusUserDto) {
        return new StatusUser(
                statusUserDto.getId(),
                statusUserDto.getName());
    }
}
