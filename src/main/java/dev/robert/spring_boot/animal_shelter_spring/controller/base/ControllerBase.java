package dev.robert.spring_boot.animal_shelter_spring.controller.base;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.robert.spring_boot.animal_shelter_spring.service.base.ServiceInterface;

public abstract class ControllerBase<ENTITY, REQDTO, RESDTO, ID> implements ControllerInterface<ENTITY, REQDTO, RESDTO, ID>{
    protected abstract ServiceInterface<ENTITY, REQDTO, RESDTO, ID> getService();

    @PostMapping
    @Override
    public ResponseEntity<RESDTO> create(@RequestBody REQDTO req) {
        return ResponseEntity.ok(
            getService().save(req)
        );
    }

    @DeleteMapping
    @Override
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if(getService().existsById(id)){
            getService().deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Override
    public ResponseEntity<List<RESDTO>> getAll() {
        return ResponseEntity.ok(
            getService().findAll()
        );
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<RESDTO> findById(@PathVariable ID id) {
        return ResponseEntity.ok(
            getService().findById(id)
        );
    }

    @PutMapping
    @Override
    public ResponseEntity<RESDTO> update(@PathVariable ID id, @RequestBody REQDTO req) {
         if(getService().existsById(id)){
             return ResponseEntity.ok(
                getService().save(req)
            );
        }
        else return ResponseEntity.notFound().build();
    }



}
