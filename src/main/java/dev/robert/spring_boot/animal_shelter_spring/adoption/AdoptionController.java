package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/adoption/admin")
public class AdoptionController extends ControllerBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long
>{

    public AdoptionController(AdoptionService service) {
        super(service);
    }

    @PatchMapping("patch/{field}/{id}")
    public AdoptionResponseDTO patch(@PathVariable String field, @PathVariable Long id, @RequestBody AdoptionRequestDTO adoptionRequestDTO) {
        return service.patch(id, field, adoptionRequestDTO);
    }

    @PostMapping
    @Override
    public ResponseEntity<AdoptionResponseDTO> create(@RequestBody AdoptionRequestDTO req){
        return ResponseEntity.ok(
                ((AdoptionService) service).post(req)
        );
    }

    @GetMapping("schedule/{date}")
    public ResponseEntity<List<AdoptionResponseDTO>> getDaySchedule(@PathVariable LocalDate date){
        return ResponseEntity.ok(
                ((AdoptionService) service).getDaySchedule(date)
        );
    }

}
