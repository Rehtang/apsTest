package ru.rehtang.second.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Getter
@AllArgsConstructor
@Transactional
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

  private final String username;
  private final String password;
  private final boolean isEnabled;
  private final Collection<? extends GrantedAuthority> authorities;

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
