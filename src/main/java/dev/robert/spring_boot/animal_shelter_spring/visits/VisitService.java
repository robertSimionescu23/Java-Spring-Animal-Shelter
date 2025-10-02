package dev.robert.spring_boot.animal_shelter_spring.visits;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;

@Service
public class VisitService extends ServiceBase<Visit, VisitRequestDTO, VisitResponseDTO, Long, VisitRepository>{

    public VisitService(VisitRepository visitRepository, VisitMapper mapper){
        super(visitRepository, mapper);
    }

    @Autowired AnimalRepository animalRepository;

    @Override
    public VisitResponseDTO patch(Long id, String field, VisitRequestDTO req){
        Visit existingVisit = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Visit with id +" + id + " not found"));
        switch(field){
            case "date" ->{
                if(req.getDate() != null)
                    existingVisit.setDate(req.getDate());
                else throw new ResourceNotFoundException("Date field of request was empty.");
            }
            case "startTime" -> {
                if(req.getStartTime() != null){
                    req.setEndTime(existingVisit.getEndTime()); //Set the ending time of the req to the visit's initial ending
                    if(existingVisit.getAnimal() != null)
                        req.setAnimalId(existingVisit.getAnimal().getId()); //Check the scheduling for a specific animal.
                    if(checkScheduleOverlapForAnimal(req)){
                        existingVisit.setStartTime(req.getStartTime());
                    }
                    else throw new IllegalArgumentException("New Visit start time overlaps another visit for this animal");
                }
            }
            case "endTime" -> {
                if(req.getEndTime() != null){
                    req.setStartTime(existingVisit.getStartTime()); //Set the ending time of the req to the visit's initial ending
                    if(existingVisit.getAnimal() != null)
                        req.setAnimalId(existingVisit.getAnimal().getId()); //Check the scheduling for a specific animal.
                    if(checkScheduleOverlapForAnimal(req)){
                        existingVisit.setEndTime(req.getEndTime());
                    }
                    else throw new IllegalArgumentException("New Visit end time overlaps another visit for this animal");
                }
            }
            case "isCancelled" ->{
                if(req.getIsCancelled() != null)
                    existingVisit.setIsCancelled(req.getIsCancelled());
                else throw new IllegalArgumentException("The cancellation status field is empty.");
            }
            default -> throw new IllegalStateException("Field " + field + " cannot be updated or does not exist.");
        }

        Visit saved = repository.save(existingVisit);
        return mapper.toDTO(saved);
    }

    @Override
    public VisitResponseDTO save(VisitRequestDTO dto){
        if(!(checkScheduleOverlapForAnimal(dto))){
            throw new ResourceNotFoundException("Visit time overlaps with another visit for the same animal");
        }
        // Map DTO to entity
        Visit visit = mapper.toEntity(dto);
        // Calculate minuteDuration (will be set by setters)
        visit.setStartTime(dto.getStartTime());
        visit.setEndTime(dto.getEndTime());
        // Set other fields
        visit.setDate(dto.getDate());
        visit.setVisitorName(dto.getVisitorName());
        visit.setIsCancelled(false);
        // Set animal if animalId is present
        if (dto.getAnimalId() != 0) {
            Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id " + dto.getAnimalId()));
            visit.setAnimal(animal);
        } else {
            visit.setAnimal(null);
        }
        // Save entity
        Visit saved = repository.save(visit);
        // Map to response DTO
        return mapper.toDTO(saved);
    }

    public boolean checkScheduleOverlapForAnimal(VisitRequestDTO dto){
        List<Visit> schedule;

        if(dto.getAnimalId() == 0)
            return true;
        else
            schedule = repository.findByAnimalId(dto.getAnimalId());

        for (Visit iter: schedule){
            if(iter.getIsCancelled() == true)
                continue;
            else {
                //check start time is not overlapping another interval
                if(dto.getStartTime().isAfter(iter.getStartTime()) && dto.getStartTime().isBefore(iter.getEndTime())){
                    return false;
                }
                //check end time is not overlapping another interval
                if(dto.getEndTime().isAfter(iter.getStartTime()) && dto.getEndTime().isBefore(iter.getEndTime())){
                    return false;
                }
                //Check that there is no direct overlap
                if(dto.getStartTime().equals(iter.getStartTime()) && dto.getEndTime().equals(iter.getEndTime()))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public List<VisitResponseDTO> getAnimalVisitSchedule(LocalDate date, Long animalId){
        List<Visit> schedule = repository.findByAnimalIdAndDate(animalId, date);
        return schedule.stream().map(mapper::toDTO).toList();
    }

    public List<VisitResponseDTO> getDaySchedule(LocalDate date){
        List<Visit> schedule = repository.findByDate(date);
        return schedule.stream().map(mapper::toDTO).toList();
    }
}
