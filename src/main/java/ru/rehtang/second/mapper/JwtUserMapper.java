package ru.rehtang.second.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.rehtang.second.persistance.model.RoleModel;
import ru.rehtang.second.persistance.model.UserModel;
import ru.rehtang.second.security.UserDetails;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class JwtUserMapper {

  protected static List<GrantedAuthority> mapToGrantedAuthority(Set<RoleModel> userRoleEntities) {
    return userRoleEntities.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
  }

  @Mapping(target = "authorities", expression = "java(mapToGrantedAuthority(user.getRoles()))")
  @Mapping(
      target = "isEnabled",
      expression = "java(UserModel.Status.ACTIVE.equals(user.getStatus()))")
  public abstract UserDetails toUserDetails(UserModel user);
}
