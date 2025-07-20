package dev.robert.spring_boot.animal_shelter_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.robert.spring_boot.animal_shelter_spring.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{

}
