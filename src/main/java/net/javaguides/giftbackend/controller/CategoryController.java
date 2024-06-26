package net.javaguides.giftbackend.controller;


import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.CategoryDto;
import net.javaguides.giftbackend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategorys();
        return ResponseEntity.ok(categoryDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateActive(@PathVariable Long id, @RequestParam Boolean active){
        CategoryDto categoryDto = categoryService.updateActive(id, active);
        return ResponseEntity.ok(categoryDto);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }
}
