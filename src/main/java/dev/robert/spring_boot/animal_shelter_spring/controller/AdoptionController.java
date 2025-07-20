package dev.robert.spring_boot.animal_shelter_spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.robert.spring_boot.animal_shelter_spring.controller.base.ControllerBase;
import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionRequestDTO;
import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionResponseDTO;
import dev.robert.spring_boot.animal_shelter_spring.model.Adoption;
import dev.robert.spring_boot.animal_shelter_spring.service.AdoptionService;

@RestController
@RequestMapping("api/v1/adoption")
public class AdoptionController extends ControllerBase<Adoption, AdoptionRequestDTO, AdoptionResponseDTO, Integer>{
    private AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @Override
    public AdoptionService getService() {
        return adoptionService;
    }

}
