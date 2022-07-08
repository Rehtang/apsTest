package ru.rehtang.second.mapper;

import io.micrometer.core.instrument.util.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.persistance.model.AnimalModel;

@Mapper(componentModel = "spring", imports = StringUtils.class)
public abstract class AnimalMapper {
  @Mapping(target = "users", ignore = true)
  @Mapping(target = "birthday", expression = "java(dto.getBirthday().toString())")
  public abstract AnimalModel toModel(AnimalDto dto);

  public abstract AnimalDto toDto(AnimalModel model);

  @Mapping(target = "users", ignore = true)
  @Mapping(
      target = "name",
      expression = "java(StringUtils.isNotBlank(dto.getName()) ? dto.getName() : before.getName())")
  @Mapping(
      target = "birthday",
      expression =
          "java(StringUtils.isNotBlank(dto.getBirthday().toString()) ? dto.getBirthday().toString() : before.getBirthday())")
  @Mapping(
      target = "sex",
      expression = "java(StringUtils.isNotBlank(dto.getSex()) ? dto.getSex() : before.getSex())")
  @Mapping(
      target = "type",
      expression = "java(StringUtils.isNotBlank(dto.getType()) ? dto.getType() : before.getType())")
  public abstract AnimalModel edit(AnimalModel before, AnimalDto dto);
}
