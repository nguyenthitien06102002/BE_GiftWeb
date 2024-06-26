package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.DistrictsDto;
import net.javaguides.giftbackend.service.DistrictsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController

@AllArgsConstructor
@RequestMapping("/api/districts")
public class districtsController {

    private final DistrictsService districtsService;

    @GetMapping("{provinceID}")
    public ResponseEntity<List<DistrictsDto>> getDistrictsByProvinceID(@PathVariable("provinceID") Long provinceID){
        List<DistrictsDto> districtsDto = districtsService.getDistrictsByProvinceID(provinceID);
        return ResponseEntity.ok(districtsDto);
    }


}
