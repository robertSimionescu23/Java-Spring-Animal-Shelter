package dev.robert.spring_boot.animal_shelter_spring.visits;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByDate(LocalDate date);
}
