package dev.robert.spring_boot.animal_shelter_spring.visits;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;

@Mapper(componentModel = "spring")
public interface VisitMapper extends MapperInterface<
    VisitRequestDTO,
    VisitResponseDTO,
    Visit
>{
    @Override
    @Mapping(target = "animalId", source = "animal.id")
    VisitResponseDTO toDTO(Visit visit);

    // We ignore animal here because we only have animalId in DTO.
    @Override
    @Mapping(target = "animal", ignore = true)
    Visit toEntity(VisitRequestDTO dto);
}
