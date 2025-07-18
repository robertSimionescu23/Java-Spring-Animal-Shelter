package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

}
