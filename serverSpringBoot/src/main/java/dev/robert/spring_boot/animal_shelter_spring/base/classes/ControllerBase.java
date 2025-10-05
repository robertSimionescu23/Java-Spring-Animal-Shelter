package dev.robert.spring_boot.animal_shelter_spring.base.classes;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.ControllerInterface;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.DTOInterface;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.ServiceInterface;

public abstract class ControllerBase<
    ENTITY,
    REQDTO extends DTOInterface<ID>,
    RESDTO extends DTOInterface<ID>,
    ID
> implements ControllerInterface<
    ENTITY,
    REQDTO,
    RESDTO,
    ID
>{
    protected ServiceInterface<ENTITY, REQDTO, RESDTO, ID>  service;

    public ControllerBase(ServiceInterface<ENTITY, REQDTO, RESDTO, ID> service) {
        this.service = service;
    }

    @PostMapping("admin")
    @Override
    public ResponseEntity<RESDTO> create(@RequestBody REQDTO req) {
        return ResponseEntity.ok(
            service.save(req)
        );
    }

    @DeleteMapping("admin/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if(service.existsById(id)){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("public")
    @Override
    public ResponseEntity<List<RESDTO>> getAll() {
        return ResponseEntity.ok(
            service.findAll()
        );
    }

    @GetMapping("public/{id}")
    @Override
    public ResponseEntity<RESDTO> findById(@PathVariable ID id) {
        return ResponseEntity.ok(
            service.findById(id)
        );
    }

    @PutMapping("admin/{id}")
    @Override
    public ResponseEntity<RESDTO> update(@PathVariable ID id, @RequestBody REQDTO req) {
        //Add the id if it's not in the body as well
        if (req.getId() == null)
            req.setId(id);
        if(service.existsById(id)){
             return ResponseEntity.ok(
                service.save(req)
            );
        }
        else return ResponseEntity.notFound().build();
    }



}
