package ru.rehtang.second.mapper;

import org.mapstruct.Mapper;
import ru.rehtang.second.dto.AnimalDto;
import ru.rehtang.second.persistance.model.AnimalModel;

@Mapper(componentModel = "spring")
public abstract class AnimalMapper {

    public abstract AnimalModel toModel(AnimalDto dto);

    public abstract AnimalDto toDto(AnimalModel model);
}
