package ru.rehtang.second.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.dto.JwtResponse;
import ru.rehtang.second.dto.OwnDto;
import ru.rehtang.second.dto.UserDto;
import ru.rehtang.second.mapper.AnimalMapper;
import ru.rehtang.second.security.UserDetails;
import ru.rehtang.second.security.jwt.JwtUtils;
import ru.rehtang.second.service.AnimalService;
import ru.rehtang.second.service.OwnedService;
import ru.rehtang.second.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final AnimalService animalService;
  private final UserService userService;
  private final AnimalMapper animalMapper;
  private final OwnedService ownedService;

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
  public String checkUsername(@RequestParam String username) {
    return userService.validateUniqueUsername(username);
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDto user) {
    userService.register(user);
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse().setToken(jwt).setUsername(userDetails.getUsername()).setRoles(roles));
  }

  @PatchMapping("/{username}")
  public UserDto editUser(@PathVariable String username, @RequestBody UserDto user) {
    return userService.editUserData(user, username);
  }

  @PostMapping("/addToOwned")
  public void addToOwned(@RequestBody OwnDto dto) {
    ownedService.addOwned(dto);
  }

  @PostMapping("/removeFromOwned")
  public void removeFromOwned(@RequestBody OwnDto dto) {
    ownedService.removeOwned(dto);
  }

  @DeleteMapping("/delete/{username}")
  public void deleteUser(@PathVariable String username) {
    userService.deleteById(username);
  }
}
