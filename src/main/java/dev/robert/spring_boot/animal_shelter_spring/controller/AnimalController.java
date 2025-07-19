package dev.robert.spring_boot.animal_shelter_spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.robert.spring_boot.animal_shelter_spring.dto.AnimalDTO;
import dev.robert.spring_boot.animal_shelter_spring.mapper.AnimalMapper;
import dev.robert.spring_boot.animal_shelter_spring.service.AnimalService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1/animal")
public class AnimalController {

    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
    }

    public AnimalService getAnimalService() {
        return animalService;
    }

    public AnimalMapper getAnimalMapper() {
        return animalMapper;
    }

    @GetMapping
    public List<AnimalDTO> getAllAnimals(){
        return getAnimalService().findAll();
    }

    @PostMapping
    public AnimalDTO postMethodName(@Valid @RequestBody AnimalDTO dto) {
        return getAnimalService().save(dto);
    }

    @GetMapping("{id}")
    public AnimalDTO getMethodName(@PathVariable Integer id) {
        return getAnimalService().findById(id);
    }


}
