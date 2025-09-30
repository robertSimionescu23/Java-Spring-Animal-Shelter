package dev.robert.spring_boot.animal_shelter_spring.adoption;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
// @VisitDurationConstr
// @ScheduleStartValidConstr //Make sure start time is after end time
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AdoptionStatusEnum status;

    @NotBlank
    private String adopterName;

    @NotBlank
    private String adopterContact;

    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false, unique = false)
    private Animal animal;


    public String getAdopterContact() {
        return adopterContact;
    }
    public void setAdopterContact(String adopterContact) {
        this.adopterContact = adopterContact;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }

    public Adoption(Long id, LocalDate date, AdoptionStatusEnum status, String adopterName, String adopterContact, Animal animal, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.adopterName = adopterName;
        this.adopterContact = adopterContact;
        this.animal = animal;
    }

    public Adoption() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Adoption adoption = (Adoption) o;
        return Objects.equals(id, adoption.id) && Objects.equals(date, adoption.date) && status == adoption.status && Objects.equals(adopterName, adoption.adopterName) && Objects.equals(adopterContact, adoption.adopterContact) && Objects.equals(animal, adoption.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, status, adopterName, adopterContact, animal);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public AdoptionStatusEnum getStatus() {
        return status;
    }
    public void setStatus(AdoptionStatusEnum status) {
        this.status = status;
    }
    public String getAdopterName() {
        return adopterName;
    }
    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
