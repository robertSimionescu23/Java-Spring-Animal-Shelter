package dev.robert.spring_boot.animal_shelter_spring.dto;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.defs.AdoptionStatus;
import dev.robert.spring_boot.animal_shelter_spring.dto.base.DTOInterface;

public class AdoptionResponseDTO implements DTOInterface<Integer>{
    private Integer id;
    private LocalDate date;
    private AdoptionStatus status;
    private String adopterName;
    private Integer animalId;
    public AdoptionResponseDTO() {
    }
    public AdoptionResponseDTO(Integer id, LocalDate date, AdoptionStatus status, String adopterName,
            Integer animalId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.adopterName = adopterName;
        this.animalId = animalId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public AdoptionStatus getStatus() {
        return status;
    }
    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }
    public String getAdopterName() {
        return adopterName;
    }
    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }
    public Integer getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
}
