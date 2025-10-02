package dev.robert.spring_boot.animal_shelter_spring.visits;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import dev.robert.spring_boot.animal_shelter_spring.visits.time_validation.ProgramConstr;
import dev.robert.spring_boot.animal_shelter_spring.visits.time_validation.ScheduleStartValidConstr;
import dev.robert.spring_boot.animal_shelter_spring.visits.time_validation.VisitDurationConstr;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;


@ScheduleStartValidConstr
@VisitDurationConstr
// @ProgramConstr TODO: Look into this
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private long minuteDuration;

    private Boolean isCancelled = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "animal_id", nullable = true, unique = false)
    private Animal animal;

    @NotNull
    private String visitorName;

    public Visit() {
    }

    public Visit(long id, @NotNull LocalTime startTime, @NotNull LocalTime endTime, Animal animal, long minuteDuration, Boolean isCancelled, LocalDate date, String visitorName) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.minuteDuration = minuteDuration;
        this.isCancelled = isCancelled;
        this.visitorName = visitorName;
        this.animal = animal;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        updateMinuteDuration();
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
        updateMinuteDuration();
    }

    public void setMinuteDuration(int duration) {
        this.minuteDuration = duration;
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    private void updateMinuteDuration() {
        if (this.startTime != null && this.endTime != null) {
            this.minuteDuration = Duration.between(this.startTime, this.endTime).toMinutes();
        }
    }








}
