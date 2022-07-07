package ru.rehtang.second.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
  @JsonProperty private AnimalType type;

  @JsonProperty private LocalDate birthday;

  @JsonProperty private Sex sex;

  @JsonProperty private String name;
}
