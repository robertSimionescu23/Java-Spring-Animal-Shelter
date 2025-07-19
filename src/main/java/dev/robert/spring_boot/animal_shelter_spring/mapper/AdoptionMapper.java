package dev.robert.spring_boot.animal_shelter_spring.mapper;

import org.springframework.stereotype.Component;

import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionRequestDTO;
import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionResponseDTO;
import dev.robert.spring_boot.animal_shelter_spring.model.Adoption;
import dev.robert.spring_boot.animal_shelter_spring.repository.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.service.AnimalService;

@Component
public class AdoptionMapper {
    public AdoptionMapper(AnimalService animalService) {
        this.animalService = animalService;
    }

    private final AnimalService animalService;
    public Adoption toEntity(AdoptionRequestDTO dto){
        Adoption adoption = new Adoption();
        adoption.setId(dto.getId());
        adoption.setDate(dto.getDate());
        adoption.setStatus(dto.getStatus());
        adoption.setAdopterName(dto.getAdopterName());
        // adoption.setAnimal(AnimalService.findById(dto.getAnimalId())); TODO: Figure this out
        adoption.setId(dto.getId());

        return adoption;
    }

    public AdoptionResponseDTO toDTO(Adoption adoption){
        AdoptionResponseDTO dto = new AdoptionResponseDTO();
        dto.setId(adoption.getId());
        dto.setDate(adoption.getDate());
        dto.setStatus(adoption.getStatus());
        dto.setAdopterName(adoption.getAdopterName());
        dto.setAnimalId(adoption.getAnimal().getId());

        return dto;
    }

    public AnimalService getAnimalService() {
        return animalService;
    }

    //TODO: Implement toEntity methods. (Figure out if I need 2 or 1)

}
