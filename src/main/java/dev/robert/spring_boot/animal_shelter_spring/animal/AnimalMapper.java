package dev.robert.spring_boot.animal_shelter_spring.animal;

import dev.robert.spring_boot.animal_shelter_spring.adoption.Adoption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface AnimalMapper extends MapperInterface<
    AnimalRequestDTO,
    AnimalResponseDTO,
    Animal
>{
    @Override
    @Mapping(target = "adoptionIds", source = "adoptions")
    AnimalResponseDTO toDTO(Animal animal);

    default List<Long> mapAdoptionIds(List<Adoption> adoptions) {
        if (adoptions == null) { return null; }

        return adoptions.stream().map(Adoption::getId).collect(Collectors.toList());
    }

    // We ignore adoption here because we only have adoptionId in DTO.
    @Override
    @Mapping(target = "adoptions", ignore = true)
    Animal toEntity(AnimalRequestDTO dto);
}
