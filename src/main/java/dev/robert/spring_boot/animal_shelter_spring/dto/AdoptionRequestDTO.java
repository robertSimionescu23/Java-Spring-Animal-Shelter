package dev.robert.spring_boot.animal_shelter_spring.dto;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.defs.AdoptionStatus;
import dev.robert.spring_boot.animal_shelter_spring.dto.base.DTOBase;

public class AdoptionRequestDTO extends DTOBase<Long>{
    private LocalDate date;
    private AdoptionStatus status;
    private String adopterName;
    private Long animalId;

    public AdoptionRequestDTO() {
    }
    public AdoptionRequestDTO(LocalDate date, AdoptionStatus status, String adopterName, String adopterCnp,
            Long animalId) {
        this.date = date;
        this.status = status;
        this.adopterName = adopterName;
        this.animalId = animalId;
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
    public Long getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

}
