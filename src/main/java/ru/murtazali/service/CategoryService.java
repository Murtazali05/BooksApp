package ru.murtazali.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.murtazali.persistense.entity.CategoryEntity;
import ru.murtazali.persistense.repository.CategoryRepository;
import ru.murtazali.service.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategories(){
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (CategoryEntity category: categoryEntityList) {
            categoryDTOList.add(new CategoryDTO(category));
        }

        return categoryDTOList;
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategory(String name){
        return new CategoryDTO(categoryRepository.findByUrlCategory(name));
    }

}
