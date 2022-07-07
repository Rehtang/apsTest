package ru.rehtang.second.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.persistance.model.AnimalModel;

@Mapper(componentModel = "spring")
public abstract class AnimalMapper {

  public abstract AnimalModel toModel(AnimalDto dto);

  public abstract AnimalDto toDto(AnimalModel model);

  @Mapping(target = "users", ignore = true)
  @Mapping(
      target = "name",
      expression = "java(StringUtils.isNotBlank(dto.getName()) ? dto.getName() : before.getName())")
  @Mapping(
      target = "birthday",
      expression =
          "java(StringUtils.isNotBlank(dto.getBirthday()) ? dto.getBirthday() : before.getBirthday())")
  @Mapping(
      target = "sex",
      expression = "java(StringUtils.isNotBlank(dto.getSex()) ? dto.getSex() : before.getSex())")
  @Mapping(
      target = "type",
      expression = "java(StringUtils.isNotBlank(dto.getType()) ? dto.getType() : before.getType())")
  public abstract AnimalModel edit(AnimalModel before, AnimalDto dto);
}
