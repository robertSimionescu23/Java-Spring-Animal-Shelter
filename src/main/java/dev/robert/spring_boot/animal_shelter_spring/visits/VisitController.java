package dev.robert.spring_boot.animal_shelter_spring.visits;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ControllerBase;

@RestController
@RequestMapping("api/v1/visit")
public class VisitController extends ControllerBase<
    Visit,
    VisitRequestDTO,
    VisitResponseDTO,
    Long
>{
    public VisitController(VisitService visitService) {
        super(visitService);
    }

    @PatchMapping("/admin/patch/{field}/{id}")
    public ResponseEntity<VisitResponseDTO>  patch(@PathVariable long id, @PathVariable String field, @RequestBody VisitRequestDTO visitDto){
        VisitResponseDTO response = service.patch(id, field, visitDto);
        return ResponseEntity.ok(response);
    }

    //TODO: Implement
    // @GetMapping("/admin/schedule/{date}")
    // public ResponseEntity<List<AdoptionResponseDTO>> getDaySchedule(@PathVariable LocalDate date){
    //     return ResponseEntity.ok(
    //             ((AdoptionService) service).getDaySchedule(date)
    //     );
    // }

}
