package dev.robert.spring_boot.animal_shelter_spring.animal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;


@RestController
@RequestMapping("api/v1/animal")
public class AnimalController extends ControllerBase<
    Animal,
    AnimalDTO,
    AnimalDTO,
    Long
>{

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
    }

    public AnimalService getService() {
        return animalService;
    }


}
