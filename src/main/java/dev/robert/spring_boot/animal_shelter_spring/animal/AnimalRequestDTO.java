package dev.robert.spring_boot.animal_shelter_spring.animal;

import java.util.List;

import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.DTOInterface;

public class AnimalRequestDTO implements DTOInterface<Long>{
    private Long id;
    private String name;
    private Integer age;
    private String species;
    private List<Long> adoptionIds;
    private String description;
    private List<String> pictureURLs;

    public AnimalRequestDTO() {
    }

    public AnimalRequestDTO(Long id, String name, Integer age, String species, List<Long> adoptionId, String description, List<String> pictureURLs) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.adoptionIds = adoptionId;
        this.description = description;
        this.pictureURLs = pictureURLs;
    }

    public List<Long> getAdoptionIds() {
        return adoptionIds;
    }

    public void setAdoptionIds(List<Long> adoptionIds) {
        this.adoptionIds = adoptionIds;
    }

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


}
