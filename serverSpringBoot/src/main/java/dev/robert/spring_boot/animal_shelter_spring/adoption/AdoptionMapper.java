package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;

@Mapper(componentModel = "spring")
public interface AdoptionMapper extends MapperInterface<
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Adoption
>{
    @Override
    @Mapping(target = "animalId", source = "animal.id")
    AdoptionResponseDTO toDTO(Adoption adoption);

    // We ignore animal here because we only have animalId in DTO.
    @Override
    @Mapping(target = "animal", ignore = true)
    Adoption toEntity(AdoptionRequestDTO dto);
}
