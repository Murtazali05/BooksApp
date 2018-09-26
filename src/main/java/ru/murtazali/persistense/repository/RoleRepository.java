package ru.murtazali.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.murtazali.persistense.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
