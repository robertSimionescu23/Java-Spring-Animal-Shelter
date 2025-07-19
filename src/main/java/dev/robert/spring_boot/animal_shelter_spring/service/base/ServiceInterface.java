package dev.robert.spring_boot.animal_shelter_spring.service.base;

import java.util.List;


public interface ServiceInterface<ENTITY, REQDTO, RESDTO, ID> {
    RESDTO save(REQDTO dto);
    RESDTO findById(ID id);
    List<RESDTO> findAll();
    void deleteById(ID id);
}
