package dev.robert.spring_boot.animal_shelter_spring.base.classes;

import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.DTOInterface;

public class DTOBase<ID> implements DTOInterface<ID>{
    ID id;

    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
