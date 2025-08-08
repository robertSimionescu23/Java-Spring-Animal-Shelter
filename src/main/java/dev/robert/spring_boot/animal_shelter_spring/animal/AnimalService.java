package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;

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
