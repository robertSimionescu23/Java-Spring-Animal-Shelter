package dev.robert.spring_boot.animal_shelter_spring.dto;

import dev.robert.spring_boot.animal_shelter_spring.dto.base.DTOInterface;

public class AnimalDTO implements DTOInterface<Integer>{
    private Integer id;
    private String name;
    private Integer age;
    private String species;
    private Integer adoptionId;

    public AnimalDTO() {
    }

    public AnimalDTO(Integer id, String name, Integer age, String species, Integer adoptionId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.adoptionId = adoptionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(Integer adoptionId) {
        this.adoptionId = adoptionId;
    }

}
