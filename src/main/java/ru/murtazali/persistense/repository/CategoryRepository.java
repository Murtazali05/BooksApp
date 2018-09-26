package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findByUrlCategory(String name);

}
