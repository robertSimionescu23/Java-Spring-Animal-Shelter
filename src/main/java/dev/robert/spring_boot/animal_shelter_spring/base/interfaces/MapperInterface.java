package dev.robert.spring_boot.animal_shelter_spring.base.interfaces;

public interface MapperInterface<REQ, RES, ENTITY> {
    ENTITY toEntity(REQ requestDto);
    RES toDTO(ENTITY entity);
}
