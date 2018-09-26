package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.BookEntity;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query("SELECT DISTINCT b FROM BookEntity b INNER JOIN b.categories c WHERE c.urlCategory=:code")
    List<BookEntity> findAllByURLCategory(@Param("code") String code);

    @Query("SELECT b FROM BookEntity b INNER JOIN b.authors a WHERE a.id=:authorId")
    List<BookEntity> findAllByAuthorId(@Param("authorId") Integer authorId);

    @Query("SELECT b FROM BookEntity b WHERE b.title LIKE %:query%")
    List<BookEntity> findBooksByQuery(@Param("query") String query);

}