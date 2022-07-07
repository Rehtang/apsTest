package ru.rehtang.second.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.mapper.AnimalMapper;
import ru.rehtang.second.service.AnimalService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

  private final AnimalService service;
  private final AnimalMapper mapper;

  @PostMapping(value = "/createAnimal", produces = MediaType.APPLICATION_JSON_VALUE)
  public void createAnimal(@RequestBody AnimalDto dto) {

    service.createAnimal(dto);
  }

  @PatchMapping(value = "/editAnimal", produces = MediaType.APPLICATION_JSON_VALUE)
  public void editAnimal(@RequestBody AnimalDto dto) {
    service.editAnimal(dto);
  }

  @DeleteMapping("/delete/{name}")
  public void deleteAnimal(@PathVariable String name) {
    service.deleteAnimal(name);
  }

  @GetMapping(value = "/findOwnedList/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AnimalDto> findOwnAnimals(@PathVariable String username) {
    return service.findOwned(username);
  }

  @GetMapping("/{name}")
  public AnimalDto findAnimalByName(@PathVariable String name) {
    return mapper.toDto(service.findAnimal(name));
  }
}
