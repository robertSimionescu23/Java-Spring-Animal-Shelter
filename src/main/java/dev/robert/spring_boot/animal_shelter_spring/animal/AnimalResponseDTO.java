package dev.robert.spring_boot.animal_shelter_spring.animal;

import java.util.List;

import dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.DTOInterface;

public class AnimalResponseDTO implements DTOInterface<Long>{
    private Long id;
    private String name;
    private Integer age;
    private String species;
    private AdoptionStatusEnum adoptionStatus;
    private String description;
    private List<String> pictureURLs;

    public AnimalResponseDTO() {
    }


    public AnimalResponseDTO(Long id, String name, Integer age, String species, AdoptionStatusEnum adoptionStatus,
            String description, List<String> pictureURLs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.adoptionStatus = adoptionStatus;
        this.description = description;
        this.pictureURLs = pictureURLs;
    }


    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    public List<String> getPictureURLs() {
        return pictureURLs;
    }


    public void setPictureURLs(List<String> pictureURLs) {
        this.pictureURLs = pictureURLs;
    }


    public AdoptionStatusEnum getAdoptionStatus() {
        return adoptionStatus;
    }


    public void setAdoptionStatus(AdoptionStatusEnum adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

}
