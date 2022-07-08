package ru.rehtang.second.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rehtang.second.persistance.model.AnimalModel;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, String> {}
