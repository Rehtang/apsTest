package ru.rehtang.second.mapper;

import io.micrometer.core.instrument.util.StringUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rehtang.second.dto.UserDto;
import ru.rehtang.second.persistance.model.RoleModel;
import ru.rehtang.second.persistance.model.UserModel;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.FIELD,
    uses = {RoleMapper.class},
    imports = {
      UUID.class,
      Set.class,
      RoleModel.class,
      StringUtils.class,
      LocalDateTime.class,
      UserModel.Status.class
    })
public abstract class UserMapper {

  public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Autowired protected RoleMapper roleMapper;

  @Autowired protected PasswordEncoder encoder;

  @Mapping(target = "password", ignore = true)
  public abstract UserDto toDto(UserModel user);

  @Mapping(target = "ownedAnimals", ignore = true)
  @Mapping(
      target = "roles",
      expression = "java(Set.of(roleMapper.toEntity(RoleModel.ERole.ROLE_USER)))")
  @Mapping(target = "status", expression = "java(Status.ACTIVE)")
  @Mapping(target = "password", expression = "java(encoder.encode(user.getPassword()))")
  public abstract UserModel register(UserDto user);

  @Mapping(target = "ownedAnimals", ignore = true)
  @Mapping(target = "removeFromOwned", ignore = true)
  @Mapping(
      target = "username",
      expression =
          "java(StringUtils.isNotBlank(dto.getUsername()) ? dto.getUsername() : before.getUsername())")
  @Mapping(
      target = "password",
      expression =
          "java(StringUtils.isNotBlank(dto.getPassword()) ? encoder.encode(dto.getPassword()) : before.getPassword())")
  public abstract UserModel edit(UserModel before, UserDto dto);
}
