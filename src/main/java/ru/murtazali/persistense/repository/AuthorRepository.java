package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.AuthorEntity;
import ru.murtazali.persistense.entity.BookEntity;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    @Query("SELECT DISTINCT a FROM AuthorEntity a INNER JOIN a.books b WHERE b.id=:bookId")
    List<AuthorEntity> findAllByBookId(@Param("bookId") Integer bookId);

    @Query("SELECT a FROM AuthorEntity a WHERE a.fio LIKE %:query%")
    List<AuthorEntity> findAuthorsByQuery(@Param("query") String query);

}
