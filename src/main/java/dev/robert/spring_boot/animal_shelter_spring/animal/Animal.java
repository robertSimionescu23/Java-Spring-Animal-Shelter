package dev.robert.spring_boot.animal_shelter_spring.animal;


import java.util.List;

import dev.robert.spring_boot.animal_shelter_spring.adoption.Adoption;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name needed")
    private String name;

    @Min(value = 0, message = "Age must be positive")
    private Integer age;


    @NotBlank
    private String species;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Adoption> adoptions;

    private String description;

    private List<String> pictureURLs;


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<String> getPictureURLs() {
        return pictureURLs;
    }
    public void setPictureURLs(List<String> pictureURLs) {
        this.pictureURLs = pictureURLs;
    }

    //TODO: Make sure that deleting from the url list, deletes the image from the file server

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((species == null) ? 0 : species.hashCode());
        result = prime * result + ((adoptions == null) ? 0 : adoptions.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((pictureURLs == null) ? 0 : pictureURLs.hashCode());
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
        Animal other = (Animal) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (species == null) {
            if (other.species != null)
                return false;
        } else if (!species.equals(other.species))
            return false;
        if (adoptions == null) {
            if (other.adoptions != null)
                return false;
        } else if (!adoptions.equals(other.adoptions))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (pictureURLs == null) {
            if (other.pictureURLs != null)
                return false;
        } else if (!pictureURLs.equals(other.pictureURLs))
            return false;
        return true;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Animal() {
    }

    public Animal(Long id, String name, Integer age, String species, List<Adoption> adoptions, String description, List<String> pictureURLs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.adoptions = adoptions;
        this.description = description;
        this.pictureURLs = pictureURLs;
    }

    public List<Adoption> getAdoptions() {
        return adoptions;
    }

    public void setAdoptions(List<Adoption> adoptions) {
        this.adoptions = adoptions;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }


}
