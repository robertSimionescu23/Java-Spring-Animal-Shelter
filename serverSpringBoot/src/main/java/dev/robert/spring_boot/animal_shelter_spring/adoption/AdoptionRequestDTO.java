package dev.robert.spring_boot.animal_shelter_spring.adoption;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.DTOBase;

public class AdoptionRequestDTO extends DTOBase<Long>{

    private LocalDate           date;
    private AdoptionStatusEnum  status;
    private String              adopterName;
    private Long                animalId;
    private String              adopterContact;

    public String getAdopterContact() {
        return adopterContact;
    }
    public void setAdopterContact(String adopterContact) {
        this.adopterContact = adopterContact;
    }
    public AdoptionRequestDTO() {
    }

    public AdoptionRequestDTO(LocalDate date, AdoptionStatusEnum status, String adopterName, Long animalId, String adopterContact) {
        this.date = date;
        this.status = status;
        this.adopterName = adopterName;
        this.animalId = animalId;
        this.adopterContact = adopterContact;
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
