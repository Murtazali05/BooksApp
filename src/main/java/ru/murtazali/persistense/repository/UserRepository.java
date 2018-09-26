package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);

}
