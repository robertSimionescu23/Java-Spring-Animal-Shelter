package dev.robert.spring_boot.animal_shelter_spring.animal;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimals(){
        return animalRepository.findAll();
    }

    public void addAnimal(Animal animal){
        animalRepository.save(animal);
    }

}
