package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.BookEntity;
import ru.murtazali.persistense.entity.BookshelfEntity;
import ru.murtazali.persistense.entity.ShelfEntity;
import ru.murtazali.persistense.entity.UserEntity;

import java.util.List;

@Repository
public interface BookShelfRepository extends JpaRepository<BookshelfEntity, Integer> {

    List<BookshelfEntity> findAllByUserAndShelf(UserEntity userEntity, ShelfEntity shelfEntity);

}
