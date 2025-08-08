package dev.robert.spring_boot.animal_shelter_spring.mapper;

import org.springframework.stereotype.Component;

import dev.robert.spring_boot.animal_shelter_spring.dto.AnimalPictureDTO;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;
import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.model.Animal;
import dev.robert.spring_boot.animal_shelter_spring.model.AnimalPicture;
import dev.robert.spring_boot.animal_shelter_spring.repository.AnimalRepository;

@Component
public class AnimalPictureMapper implements MapperInterface<
    AnimalPictureDTO,
    AnimalPictureDTO,
    AnimalPicture
>{
    private final AnimalRepository animalRepository;

    public AnimalPictureMapper(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public AnimalPictureDTO toDTO(AnimalPicture entity) {
        AnimalPictureDTO dto = new AnimalPictureDTO();
        dto.setId(entity.getId());
        dto.setFilename(entity.getFilename());
        dto.setSize(entity.getSize());
        dto.setPicture(entity.getPicture());
        dto.setAnimalId(entity.getAnimal().getId());

        return dto;
    }

    @Override
    public AnimalPicture toEntity(AnimalPictureDTO requestDto) {
        AnimalPicture pic = new AnimalPicture();
        pic.setId(requestDto.getId());
        pic.setSize(requestDto.getSize());
        pic.setPicture(requestDto.getPicture());
        pic.setFilename(requestDto.getFilename());
        Animal animal = getAnimalRepository().findById(requestDto.getAnimalId()).
            orElseThrow(() -> new ResourceNotFoundException("There is no animal with the specified ID"));
        pic.setAnimal(animal);

        return pic;
    }

    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }

}
