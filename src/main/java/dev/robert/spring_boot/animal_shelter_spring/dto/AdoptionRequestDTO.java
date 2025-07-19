package dev.robert.spring_boot.animal_shelter_spring.dto;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.defs.AdoptionStatus;

public class AdoptionRequestDTO {
    private Integer id;
    private LocalDate date;
    private AdoptionStatus status;
    private String adopterName;
    private String adopterCnp;
    private Integer animalId;

    public AdoptionRequestDTO() {
    }
    public AdoptionRequestDTO(Integer id, LocalDate date, AdoptionStatus status, String adopterName, String adopterCnp,
            Integer animalId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.adopterName = adopterName;
        this.adopterCnp = adopterCnp;
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
    public String getAdopterCnp() {
        return adopterCnp;
    }
    public void setAdopterCnp(String adopterCnp) {
        this.adopterCnp = adopterCnp;
    }
    public Integer getAnimalId() {
        return animalId;
    }
    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

}
