package dev.robert.spring_boot.animal_shelter_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.robert.spring_boot.animal_shelter_spring.controller.base.ControllerBase;
import dev.robert.spring_boot.animal_shelter_spring.dto.AnimalDTO;
import dev.robert.spring_boot.animal_shelter_spring.mapper.AnimalMapper;
import dev.robert.spring_boot.animal_shelter_spring.model.Animal;
import dev.robert.spring_boot.animal_shelter_spring.service.AnimalService;


@RestController
@RequestMapping("api/v1/animal")
public class AnimalController extends ControllerBase<Animal, AnimalDTO, AnimalDTO, Integer>{

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
    }

    public AnimalService getService() {
        return animalService;
    }



}
