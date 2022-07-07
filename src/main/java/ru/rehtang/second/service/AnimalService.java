package ru.rehtang.second.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.mapper.AnimalMapper;
import ru.rehtang.second.persistance.model.AnimalModel;
import ru.rehtang.second.persistance.repository.AnimalRepository;
import ru.rehtang.second.persistance.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {
  private final AnimalMapper mapper;
  private final AnimalRepository animalRepository;
  private final UserRepository userRepository;

  public void createAnimal(AnimalDto dto) {
    animalRepository.save(mapper.toModel(dto));
  }

  public void editAnimal(AnimalDto dto) {
    AnimalModel animal = findAnimal(dto.getName());
    animalRepository.save(mapper.edit(animal, dto));
  }

  public AnimalModel findAnimal(String name) {
    return animalRepository
        .findById(name)
        .orElseThrow(
            () ->
                new RuntimeException(
                    String.format("There is no animal with name = %s in Database", name)));
  }

  public void deleteAnimal(String name) {
    animalRepository.deleteById(name);
  }

  public List<AnimalDto> findOwned(String username) {
    var user =
        userRepository
            .findById(username)
            .orElseThrow(
                () ->
                    new RuntimeException(
                        String.format(
                            "There is no owned list for user with username = %s in Database",
                            username)));
    return user.getOwnedAnimals().stream().map(mapper::toDto).collect(Collectors.toList());
  }
}
