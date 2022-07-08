package ru.rehtang.second.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rehtang.second.persistance.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
  Boolean existsByUsername(String username);
}
