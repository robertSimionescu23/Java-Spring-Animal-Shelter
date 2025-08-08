package dev.robert.spring_boot.animal_shelter_spring.adoption;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.DTOBase;

public class AdoptionResponseDTO extends DTOBase<Long>{
    private LocalDate date;
    private AdoptionStatusEnum status;
    private Long animalId;
    public AdoptionResponseDTO() {
    }
    public AdoptionResponseDTO(LocalDate date, AdoptionStatusEnum status, String adopterName,
            Long animalId) {
        this.date = date;
        this.status = status;
        this.animalId = animalId;
    }
    public LocalDate getDate() {
        return date;
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
    public Long getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }
}
