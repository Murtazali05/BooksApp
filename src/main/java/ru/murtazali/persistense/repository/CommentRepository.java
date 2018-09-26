package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.CommentEntity;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query("SELECT DISTINCT c FROM CommentEntity c WHERE c.book.id=:id")
    List<CommentEntity> findAllByBookId(@Param("id") Integer id);

}
