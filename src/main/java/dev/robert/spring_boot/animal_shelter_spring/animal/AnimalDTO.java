package dev.robert.spring_boot.animal_shelter_spring.animal;

import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.DTOInterface;

public class AnimalDTO implements DTOInterface<Long>{
    private Long id;
    private String name;
    private Integer age;
    private String species;
    private Long adoptionId;

    public AnimalDTO() {
    }

    public AnimalDTO(Long id, String name, Integer age, String species, Long adoptionId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.species = species;
        this.adoptionId = adoptionId;
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

    public Long getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(Long adoptionId) {
        this.adoptionId = adoptionId;
    }

}
