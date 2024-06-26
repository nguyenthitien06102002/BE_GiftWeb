package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ProductTopicDto;
import net.javaguides.giftbackend.service.ProductTopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class ProductTopicController {
    private  final ProductTopicService productTopicService;

    //create
    @PostMapping
    public ResponseEntity<ProductTopicDto> createTopic(@RequestBody ProductTopicDto productTopicDto){
        ProductTopicDto saved = productTopicService.createProductTopic(productTopicDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @GetMapping
    public  ResponseEntity<List<ProductTopicDto>> getAll(){
        List<ProductTopicDto> productTopicDtos = productTopicService.getAllTopic();
        return ResponseEntity.ok(productTopicDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTopicDto> updateActive(@PathVariable Long id, @RequestParam Boolean active){
        ProductTopicDto productTopicDto = productTopicService.updateActive(id, active);
        return ResponseEntity.ok(productTopicDto);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductTopicDto> updateTopic(@PathVariable Long id, @RequestBody ProductTopicDto productTopicDto){
        ProductTopicDto updated = productTopicService.updateProductTopic(id, productTopicDto);
        return ResponseEntity.ok(updated);
    }

}
