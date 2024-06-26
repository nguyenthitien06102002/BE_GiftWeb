package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.DistrictsDto;

import java.util.List;

public interface DistrictsService {

    List<DistrictsDto> getDistrictsByProvinceID(Long provinceID);



}
