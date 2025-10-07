package dev.robert.spring_boot.animal_shelter_spring.visits;
import java.time.LocalDate;
import java.time.LocalTime;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.DTOBase;

class VisitRequestDTO extends DTOBase<Long>{
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long      minuteDuration;
    private Boolean   isCancelled;
    private long      animalId;
    private String    visitorName;


    public VisitRequestDTO() {
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
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
    public long getMinuteDuration() {
        return minuteDuration;
    }
    public void setMinuteDuration(long minuteDuration) {
        this.minuteDuration = minuteDuration;
    }
    public Boolean getIsCancelled() {
        return isCancelled;
    }
    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
    public long getAnimalId() {
        return animalId;
    }
    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }
    public String getVisitorName() {
        return visitorName;
    }
    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }


}
