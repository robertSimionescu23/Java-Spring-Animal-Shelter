package dev.robert.spring_boot.animal_shelter_spring.visits;

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
    //TODO: Implement PATCH

}
