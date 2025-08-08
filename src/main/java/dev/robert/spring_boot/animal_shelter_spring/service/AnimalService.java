package dev.robert.spring_boot.animal_shelter_spring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.dto.AnimalDTO;
import dev.robert.spring_boot.animal_shelter_spring.mapper.AnimalMapper;
import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.model.Animal;
import dev.robert.spring_boot.animal_shelter_spring.repository.AdoptionRepository;
import dev.robert.spring_boot.animal_shelter_spring.repository.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.service.base.ServiceBase;

@Service
public class AnimalService extends ServiceBase<
    Animal,
    AnimalDTO,
    AnimalDTO,
    Long
>{

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Override
    protected JpaRepository<Animal, Long> getRepository(){
        return animalRepository;
    }

    @Override
    protected MapperInterface<AnimalDTO, AnimalDTO, Animal> getMapper(){
        return animalMapper;
    }

    public AnimalService(AnimalRepository animalRepository, AdoptionRepository adoptionRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }


}
