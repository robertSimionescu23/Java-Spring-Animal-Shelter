package dev.robert.spring_boot.animal_shelter_spring.dto.base;

public class DTOBase<ID> implements DTOInterface<ID>{
    ID id;

    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
