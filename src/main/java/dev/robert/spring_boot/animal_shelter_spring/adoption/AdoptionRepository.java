package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdoptionRepository extends JpaRepository<Adoption, Long>{
    List<Adoption> findByDate(LocalDate date);
}
