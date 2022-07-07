package ru.rehtang.second.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.dto.UserDto;
import ru.rehtang.second.mapper.AnimalMapper;
import ru.rehtang.second.service.AnimalService;
import ru.rehtang.second.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

  private final AnimalService animalService;
  private final UserService userService;
  private final AnimalMapper animalMapper;
  private final AuthController controller;

  @PostMapping(value = "/createAnimal", produces = MediaType.APPLICATION_JSON_VALUE)
  public void createAnimal(@RequestBody AnimalDto dto) {

    animalService.createAnimal(dto);
  }

  @PatchMapping(value = "/editAnimal", produces = MediaType.APPLICATION_JSON_VALUE)
  public void editAnimal(@RequestBody AnimalDto dto) {
    animalService.editAnimal(dto);
  }

  @DeleteMapping("/delete/{name}")
  public void deleteAnimal(@PathVariable String name) {
    animalService.deleteAnimal(name);
  }

  @GetMapping(value = "/findOwnedList/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AnimalDto> findOwnAnimals(@PathVariable String username) {
    return animalService.findOwned(username);
  }

  @GetMapping("/find/{name}")
  public AnimalDto findAnimalByName(@PathVariable String name) {
    return animalMapper.toDto(animalService.findAnimal(name));
  }

  @GetMapping("/check")
  public Boolean checkUsername(@RequestParam String username) {
    return userService.validateUniqueUsername(username);
  }

  @PostMapping("/register")
  public void registerUser(@RequestBody UserDto user) {
    userService.register(user);
    controller.authenticate(user);
  }
}
