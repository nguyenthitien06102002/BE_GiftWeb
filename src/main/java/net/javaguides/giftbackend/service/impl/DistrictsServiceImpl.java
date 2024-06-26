package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.DistrictsDto;
import net.javaguides.giftbackend.entity.Districts;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;
import net.javaguides.giftbackend.mapper.DistrictsMapper;
import net.javaguides.giftbackend.repository.DistrictsRepository;
import net.javaguides.giftbackend.service.DistrictsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class DistrictsServiceImpl implements DistrictsService {
    private DistrictsRepository districtsRepository;
    @Override
    public List<DistrictsDto> getDistrictsByProvinceID(Long provinceID) {
        List<Districts> districts = districtsRepository.findByProvinceID(provinceID);
        if(districts.isEmpty()){
            throw new ResourceNotFoundException("No districts found  with ID:" + provinceID);
        }
        return districts.stream()
                .map(DistrictsMapper::maptoDistrictsDto)
                .collect(Collectors.toList());
    }


}
