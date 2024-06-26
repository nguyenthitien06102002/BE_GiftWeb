package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.DistrictsDto;
import net.javaguides.giftbackend.entity.Districts;

public class DistrictsMapper {
    public  static DistrictsDto maptoDistrictsDto(Districts districts){
        return new DistrictsDto(
                districts.getDistrictID(),
                districts.getDistrictName(),
                districts.getType(),
                districts.getProvinceID()
        );
    }
}
