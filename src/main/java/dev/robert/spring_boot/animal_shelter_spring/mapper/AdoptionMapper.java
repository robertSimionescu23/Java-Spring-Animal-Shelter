package dev.robert.spring_boot.animal_shelter_spring.mapper;

import org.springframework.stereotype.Component;

import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionRequestDTO;
import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionResponseDTO;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;
import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.model.Adoption;
import dev.robert.spring_boot.animal_shelter_spring.model.Animal;
import dev.robert.spring_boot.animal_shelter_spring.repository.AnimalRepository;

@Component
public class AdoptionMapper implements MapperInterface<AdoptionRequestDTO, AdoptionResponseDTO, Adoption>{
    public AdoptionMapper(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    private final AnimalRepository animalRepository;

    @Override
    public Adoption toEntity(AdoptionRequestDTO dto){
        Adoption adoption = new Adoption();
        adoption.setId(dto.getId());
        adoption.setDate(dto.getDate());
        adoption.setStatus(dto.getStatus());
        adoption.setAdopterName(dto.getAdopterName());
        adoption.setAdopterCnp(dto.getAdopterCnp());
        adoption.setStatus(dto.getStatus());
        Animal animal = getAnimalRepository().findById(dto.getAnimalId())
            .orElseThrow(() -> new ResourceNotFoundException("There is no animal with the specified ID"));
        adoption.setAnimal(animal);

        return adoption;
    }

    @Override
    public AdoptionResponseDTO toDTO(Adoption adoption){
        AdoptionResponseDTO dto = new AdoptionResponseDTO();
        dto.setId(adoption.getId());
        dto.setDate(adoption.getDate());
        dto.setStatus(adoption.getStatus());
        dto.setAdopterName(adoption.getAdopterName());
        dto.setAnimalId(adoption.getAnimal().getId());

        return dto;
    }

    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }


}
