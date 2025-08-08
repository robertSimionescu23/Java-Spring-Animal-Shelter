package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.stereotype.Component;

import dev.robert.spring_boot.animal_shelter_spring.adoption.Adoption;
import dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionRepository;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;


@Component
public class AnimalMapper implements MapperInterface<
    AnimalDTO,
    AnimalDTO,
    Animal
>{
    private final AdoptionRepository adoptionRepository;


    public AnimalMapper(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    @Override
    public AnimalDTO toDTO(Animal animal){
        AnimalDTO dto = new AnimalDTO();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setAge(animal.getAge());
        dto.setSpecies(animal.getSpecies());
        if(animal.getAdoption() != null)
            dto.setAdoptionId(animal.getAdoption().getId());

        return dto;
    }

    @Override
    public Animal toEntity(AnimalDTO dto){
        Animal animalEntity = new Animal();
        Adoption adoption = null;

        if(dto.getAdoptionId() != null)
            adoption = adoptionRepository.findById(dto.getAdoptionId())
                .orElseThrow(() ->new ResourceNotFoundException("There is no adoption with this id"));
        animalEntity.setId(dto.getId());
        animalEntity.setName(dto.getName());
        animalEntity.setAge(dto.getAge());
        animalEntity.setSpecies(dto.getSpecies());
        animalEntity.setAdoption(adoption);

        return animalEntity;
    }
}
