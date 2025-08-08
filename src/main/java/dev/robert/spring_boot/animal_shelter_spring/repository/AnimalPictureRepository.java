package dev.robert.spring_boot.animal_shelter_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.robert.spring_boot.animal_shelter_spring.model.AnimalPicture;

public interface AnimalPictureRepository extends JpaRepository<AnimalPicture, Long>{

}
