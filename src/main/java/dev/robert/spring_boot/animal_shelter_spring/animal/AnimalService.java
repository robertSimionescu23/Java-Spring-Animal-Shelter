package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;

@Service
public class AnimalService extends ServiceBase<
    Animal,
    AnimalRequestDTO,
    AnimalResponseDTO,
    Long
>{

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Override
    protected JpaRepository<Animal, Long> getRepository(){
        return animalRepository;
    }

    @Override
    protected AnimalMapper getMapper(){
        return animalMapper;
    }

    public AnimalService(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository   = animalRepository;
        this.animalMapper       = animalMapper;
    }


}
