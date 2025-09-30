package dev.robert.spring_boot.animal_shelter_spring.visits;

import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;

@Service
public class VisitService extends ServiceBase<Visit, VisitRequestDTO, VisitResponseDTO, Long>{

    public VisitService(VisitRepository repo, VisitMapper mapper){
        super(repo, mapper);
    }

    @Override
    public VisitResponseDTO patch(Long id, String field, VisitRequestDTO req){
        return null; //TODO: Implement this method
    }
}
