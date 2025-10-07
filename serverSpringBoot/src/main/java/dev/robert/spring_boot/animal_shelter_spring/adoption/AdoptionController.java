package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.web.bind.annotation.*;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;

@RestController
@RequestMapping("api/v1/adoption")
public class AdoptionController extends ControllerBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long
>{

    public AdoptionController(AdoptionService service) {
        super(service);
    }

    @PatchMapping("/admin/patch/{field}/{id}")
    public AdoptionResponseDTO patch(@PathVariable String field, @PathVariable Long id, @RequestBody AdoptionRequestDTO adoptionRequestDTO) {
        return service.patch(id, field, adoptionRequestDTO);
    }

}
