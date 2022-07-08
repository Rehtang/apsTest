package ru.rehtang.second.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.rehtang.second.mapper.JwtUserMapper;
import ru.rehtang.second.security.EventListener.LoginAttemptService;
import ru.rehtang.second.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserService userService;
  private final JwtUserMapper jwtUserMapper;

  @Autowired private LoginAttemptService loginAttemptService;

  @Autowired private HttpServletRequest request;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String ip = getClientIP();
    if (loginAttemptService.isBlocked(ip)) {
      throw new RuntimeException("blocked");
    }

    try {
      return jwtUserMapper.toUserDetails(userService.findUserByUsername(username));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }

    return xfHeader.split(",")[0];
  }
}
