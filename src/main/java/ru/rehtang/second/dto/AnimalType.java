package ru.rehtang.second.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AnimalType {
  DOG("street dog"),
  CAT("Street cat"),
  PARROT("blue-and-yellow macaw");

  private final String code;
}
