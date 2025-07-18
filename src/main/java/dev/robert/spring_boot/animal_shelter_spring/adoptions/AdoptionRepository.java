package dev.robert.spring_boot.animal_shelter_spring.adoptions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRepository extends JpaRepository<Adoption, Integer>{

}
