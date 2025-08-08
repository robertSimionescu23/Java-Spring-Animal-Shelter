package dev.robert.spring_boot.animal_shelter_spring.model;

import java.time.LocalDate;

import dev.robert.spring_boot.animal_shelter_spring.defs.AdoptionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AdoptionStatus status;
    @NotBlank
    private String adopterName;

    @OneToOne(optional = false)
    @JoinColumn(name = "animal_id", nullable = false, unique = true)
    private Animal animal;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((adopterName == null) ? 0 : adopterName.hashCode());
        result = prime * result + ((animal == null) ? 0 : animal.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Adoption other = (Adoption) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (status != other.status)
            return false;
        if (adopterName == null) {
            if (other.adopterName != null)
                return false;
        } else if (!adopterName.equals(other.adopterName))
            return false;
        if (animal == null) {
            if (other.animal != null)
                return false;
        } else if (!animal.equals(other.animal))
            return false;
        return true;
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
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

}
