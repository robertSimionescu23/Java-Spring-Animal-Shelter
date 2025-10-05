package dev.robert.spring_boot.animal_shelter_spring.adoption;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Long>{
    List<Adoption> findByAnimalId(long animalId);
}
