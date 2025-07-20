package dev.robert.spring_boot.animal_shelter_spring.controller.base;

import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ControllerInterface<ENTITY, REQDTO, RESDTO, ID> {

    ResponseEntity<List<RESDTO>> getAll();

    ResponseEntity<RESDTO> findById(ID id);

    ResponseEntity<RESDTO> create(REQDTO req);

    ResponseEntity<RESDTO> update(ID id, REQDTO req);

    ResponseEntity<Void> delete(ID id);
}
