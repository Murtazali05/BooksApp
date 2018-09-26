package ru.murtazali.service.dto;

import lombok.Getter;
import lombok.Setter;
import ru.murtazali.persistense.entity.CategoryEntity;

@Getter
@Setter
public class CategoryDTO {
    private int id;
    private String name;
    private String urlCategory;

    public CategoryDTO(CategoryEntity categoryEntity) {
        id = categoryEntity.getId();
        name = categoryEntity.getName();
        urlCategory = categoryEntity.getUrlCategory();
    }
}
