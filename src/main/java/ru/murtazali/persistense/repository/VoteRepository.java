package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.CommentEntity;
import ru.murtazali.persistense.entity.UserEntity;
import ru.murtazali.persistense.entity.VoteEntity;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Integer> {

    boolean existsAllByCommentAndUser(CommentEntity comment, UserEntity user);

}
