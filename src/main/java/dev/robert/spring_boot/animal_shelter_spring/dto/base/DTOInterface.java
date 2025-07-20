package dev.robert.spring_boot.animal_shelter_spring.dto.base;

public interface DTOInterface<ID>{
    ID getId();
    void setId (ID id);
}
