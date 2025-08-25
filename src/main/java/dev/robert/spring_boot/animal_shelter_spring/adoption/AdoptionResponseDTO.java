package dev.robert.spring_boot.animal_shelter_spring.adoption;

import java.time.LocalDate;
import java.time.LocalTime;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.DTOBase;

public class AdoptionResponseDTO extends DTOBase<Long>{
    private LocalDate date;
    private AdoptionStatusEnum status;
    private Long animalId;
    private LocalTime startTime;
    private LocalTime endTime;

    public AdoptionResponseDTO() {
    }

    public AdoptionResponseDTO(LocalDate date, AdoptionStatusEnum status, Long animalId, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.status = status;
        this.animalId = animalId;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
