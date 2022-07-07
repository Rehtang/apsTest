package ru.rehtang.second.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sex {
  M("male"),
  F("female");

  private final String code;
}
