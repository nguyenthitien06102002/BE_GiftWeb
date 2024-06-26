package net.javaguides.giftbackend.service;

import net.javaguides.giftbackend.dto.CategoryDto;
import net.javaguides.giftbackend.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    List<CategoryDto> getAllCategorys();

    CategoryDto updateActive(Long id, Boolean active);

    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
}
