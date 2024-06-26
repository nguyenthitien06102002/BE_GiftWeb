package net.javaguides.giftbackend.service.impl;
import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ProvincesDto;
import net.javaguides.giftbackend.entity.Provinces;
import net.javaguides.giftbackend.mapper.ProvinceMapper;
import net.javaguides.giftbackend.repository.ProvinceRepository;
import net.javaguides.giftbackend.service.ProvinceService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {


    private ProvinceRepository provinceRepository;
    @Override
    public List<ProvincesDto> getAllProvinces() {
        List<Provinces> provinces = provinceRepository.findAll();
        return provinces.stream().map((province) -> ProvinceMapper.maptoProvincesDto(province))
                .collect(Collectors.toList());
    }
}
