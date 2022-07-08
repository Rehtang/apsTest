package ru.rehtang.second.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private String username;
  private List<String> roles;
}
