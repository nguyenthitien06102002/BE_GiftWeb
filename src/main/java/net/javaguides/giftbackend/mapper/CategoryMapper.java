package net.javaguides.giftbackend.mapper;

import net.javaguides.giftbackend.dto.CategoryDto;
import net.javaguides.giftbackend.entity.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto (Category category){
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getImage(),
                category.getActive(),
                category.getActiveHome()
        );
    }
    public static Category mapToCategory(CategoryDto categoryDto){
        return new Category(
                categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getImage(),
                categoryDto.getActive(),
                categoryDto.getActiveHome()
        );
    }
}

