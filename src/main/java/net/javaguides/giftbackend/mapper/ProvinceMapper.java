package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.ProvincesDto;
import net.javaguides.giftbackend.entity.Provinces;

public class ProvinceMapper {

    public static ProvincesDto maptoProvincesDto(Provinces provinces){
        return new ProvincesDto(
                provinces.getProvinceID(),
                provinces.getProvinceName(),
                provinces.getType()
        );
    }
}
