package ru.rehtang.second.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rehtang.second.dto.UserDto;
import ru.rehtang.second.mapper.UserMapper;
import ru.rehtang.second.persistance.model.UserModel;
import ru.rehtang.second.persistance.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserModel findUserByUsername(String username) {
    return userRepository
        .findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("No user found by username: " + username));
  }

  public void register(UserDto userDto) {
    userRepository.save(userMapper.register(userDto));
  }

  public UserDto editUserData(UserDto dto, String username) {
    UserModel user = findUserByUsername(username);
    userRepository.save(userMapper.edit(user, dto));
    return userMapper.toDto(findUserByUsername(dto.getUsername()));
  }

  public String validateUniqueUsername(String username) {
    if (userRepository.existsByUsername(username)) {
      return "Name exists";
    }
    return "Name is free";
  }

  public void deleteById(String username) {

    var entity =
        userRepository
            .findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + username));
    userRepository.delete(entity);
  }
}
