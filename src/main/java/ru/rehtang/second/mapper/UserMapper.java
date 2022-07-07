package ru.rehtang.second.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rehtang.second.dto.UserDto;
import ru.rehtang.second.persistance.model.UserModel;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class UserMapper {
  @Autowired protected PasswordEncoder encoder;

  @Mapping(target = "password", ignore = true)
  public abstract UserDto toDto(UserModel user);

  public abstract UserModel register(UserDto user);

  @Mapping(target = "removeFromOwned", ignore = true)
  @Mapping(target = "username", source = "before.username")
  @Mapping(
      target = "password",
      expression =
          "java(StringUtils.isNotBlank(dto.getPassword()) ? encoder.encode(dto.getPassword()) : before.getPassword())")
  public abstract UserModel edit(UserModel before, UserDto dto);
}
