package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ProvincesDto;
import net.javaguides.giftbackend.entity.Provinces;
import net.javaguides.giftbackend.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/province")
public class ProvincesController {

    private final ProvinceService provinceService;


    //getAllProvince
    @GetMapping
    public ResponseEntity<List<ProvincesDto>> getAllProvince (){
        List<ProvincesDto> provincesDtos = provinceService.getAllProvinces();
        return ResponseEntity.ok(provincesDtos);
    }
}
