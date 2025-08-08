package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;

@RestController
@RequestMapping("api/v1/adoption")
public class AdoptionController extends ControllerBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long
>{
    private AdoptionService adoptionService;

    public AdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @Override
    public AdoptionService getService() {
        return adoptionService;
    }

}
