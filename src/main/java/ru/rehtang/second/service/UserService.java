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

  public void editUserData(UserDto dto) {
    UserModel user = findUserByUsername(dto.getUsername());
    userRepository.save(userMapper.edit(user, dto));
  }

  public Boolean validateUniqueUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public void deleteById(String username) {
    var entity =
        userRepository
            .findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("No user found by id: " + username));
    userRepository.delete(entity);
  }
}
