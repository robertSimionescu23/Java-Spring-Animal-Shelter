package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;


@Mapper(componentModel = "spring")
public interface AnimalMapper extends MapperInterface<
    AnimalRequestDTO,
    AnimalResponseDTO,
    Animal
>{
    @Override
    @Mapping(target = "adoptionId", source = "adoption.id")
    AnimalResponseDTO toDTO(Animal animal);

    // We ignore adoption here because we only have adoptionId in DTO.
    @Override
    @Mapping(target = "adoption", ignore = true)
    Animal toEntity(AnimalRequestDTO dto);
}
