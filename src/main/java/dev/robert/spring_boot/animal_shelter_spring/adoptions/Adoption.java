package dev.robert.spring_boot.animal_shelter_spring.adoptions;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;



@Entity
public class Adoption {
    @Id
    private Integer id;
    @NotBlank
    private LocalDate date;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
    @NotBlank
    private String adopterName;
    @NotBlank
    private String adopterCnp;
    @OneToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false, unique = true)
    private Animal animal;

}
