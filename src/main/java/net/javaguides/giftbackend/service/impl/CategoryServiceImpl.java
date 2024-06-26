package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.CategoryDto;
import net.javaguides.giftbackend.entity.Category;
import net.javaguides.giftbackend.mapper.CategoryMapper;
import net.javaguides.giftbackend.repository.CategoryRepository;
import net.javaguides.giftbackend.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category saveCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(saveCategory);
    }

    @Override
    public List<CategoryDto> getAllCategorys(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> CategoryMapper.mapToCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateActive(Long id, Boolean active) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            category.setActive(active);
            Category saveCategory = categoryRepository.save(category);
            return CategoryMapper.mapToCategoryDto(saveCategory);
        }
        return null;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category != null){
            category.setName(categoryDto.getName());
            category.setActiveHome(categoryDto.getActiveHome());
            Category saveCategory = categoryRepository.save(category);
            return CategoryMapper.mapToCategoryDto(saveCategory);
        }
        return null;
    }
}
