package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.cancelled;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.pending;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.done;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.visit;

@Service
public class AdoptionService extends ServiceBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long
>{

    @Autowired private AnimalRepository animalRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper) {
        super(adoptionRepository, adoptionMapper);
    }


    //TODO: Change this to an per animal basis. There can be parallel visits to different animals.
    //TODO: Move this to Visits servie
    // public boolean checkScheduleOverlap(AdoptionRequestDTO dto){
    //     List<Adoption> schedule =  ((AdoptionRepository) repository).findByDate(dto.getDate());

    //     for (Adoption iter: schedule){
    //         if(iter.getStatus() == cancelled)
    //             continue;
    //         else {
    //             //check start time is not overlapping another interval
    //             if(dto.getStartTime().isAfter(iter.getStartTime()) && dto.getStartTime().isBefore(iter.getEndTime())){
    //                 return false;
    //             }
    //             //check end time is not overlapping another interval
    //             if(dto.getEndTime().isAfter(iter.getStartTime()) && dto.getEndTime().isBefore(iter.getEndTime())){
    //                 return false;
    //             }
    //             //Check that there is no direct overlap
    //             if(dto.getStartTime().equals(iter.getStartTime()) && dto.getEndTime().equals(iter.getEndTime()))
    //             {
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

    public AdoptionResponseDTO post(AdoptionRequestDTO dto){
        Adoption adoption = mapper.toEntity(dto);

        // Fetch the Animal entity referenced by animalId in the DTO
        Animal linkedAnimal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id " + dto.getAnimalId()));

        List<Adoption> existingAdoptions = linkedAnimal.getAdoptions();

        if(dto.getStatus().equals(cancelled) || dto.getStatus().equals(done)){
            throw new IllegalStateException("Adoptions entities may only be created with visit or pending status. The state can be altered later on.");
        }

        // if(!checkScheduleOverlap(dto))
        //     throw new IllegalStateException("The requested time slot is already filled.");

        if(dto.getStatus().equals(pending)){
            for(Adoption iterAdoption : existingAdoptions){
                if(iterAdoption.getStatus().equals(pending) || iterAdoption.getStatus().equals(done)){
                    //There cannot be 2 pending adoptions at a time or a pending adoption after a adoption already went through.
                    throw new IllegalStateException("The adoption status is already pending or done.");
                }
            }
        }
        else if(dto.getStatus().equals(visit)){
            for(Adoption iterAdoption : existingAdoptions){
                if(iterAdoption.getStatus().equals(pending)){
                    if(iterAdoption.getDate().isAfter(dto.getDate())) {
                        //There cannot be a visitation before the pending adoption request is resolved
                        throw new IllegalStateException("The visit date is after a pending adoption is scheduled. Resolve adoption request first.");
                    }
                }
                else if(iterAdoption.getStatus().equals(done)){
                    throw new IllegalStateException("The animal has already been adopted.");
                }
            }
        }

        // Set the Animal reference
        adoption.setAnimal(linkedAnimal);

        // Save Adoption entity
        Adoption savedAdoption = repository.save(adoption);

        // Set the adoption entity on referenced animal
        linkedAnimal.getAdoptions().add(savedAdoption);
        animalRepository.save(linkedAnimal);

        return mapper.toDTO(savedAdoption);
    }

    @Override
    public AdoptionResponseDTO patch(Long id, String field, AdoptionRequestDTO req){

        Adoption existingAdoption = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Adoption with id " + id + " cannot be found."));

        Animal linkedAnimal = existingAdoption.getAnimal();
        List<Adoption> existingAdoptions = linkedAnimal.getAdoptions();

        switch (field) {
            case "date" -> {
                if (req.getDate() != null)
                    existingAdoption.setDate(req.getDate());
                else throw new ResourceNotFoundException("Date field of request was empty.");
            }
            case "status" -> {
                if (req.getStatus() != null)
                    existingAdoption.setStatus(req.getStatus());
                else throw new ResourceNotFoundException("Status field of request was empty.");

                //Make sure that there are no other pending adoptions
                if(req.getStatus().equals(pending) || req.getStatus().equals(done)) {
                    for (Adoption iterAdoption : existingAdoptions) {
                        //Check all other elements
                        if(iterAdoption.getId().equals(id))
                            continue;
                        if (iterAdoption.getStatus().equals(pending) || iterAdoption.getStatus().equals(done)) {
                            throw new ResourceNotFoundException("There can only be one adoption pending or done, per animal.");
                        }
                    }
                }
            }
            case "adopterName" -> {
                if (req.getAdopterName() != null)
                    existingAdoption.setAdopterName(req.getAdopterName());
                else throw new ResourceNotFoundException("Adopter Name field of request was empty.");
            }
            case "adopterContact" -> {
                if (req.getAdopterContact() != null)
                    existingAdoption.setAdopterContact(req.getAdopterContact());
                else throw new ResourceNotFoundException("Adopter Contact field of request was empty.");
            }
            // //TODO: Move this to Visits servie
            // case "startTime" ->{
            //     if(checkScheduleOverlap(req))
            //         existingAdoption.setStartTime(req.getStartTime());
            //     else throw new IllegalStateException("The requested time slot is already filled.");
            // }
            // //TODO: Move this to Visits servie
            // case "endTime" ->{
            //     if(checkScheduleOverlap(req))
            //         existingAdoption.setEndTime(req.getStartTime());
            //     else throw new IllegalStateException("The requested time slot is already filled.");
            // }
            default -> throw new ResourceNotFoundException("Field to patch cannot be found.");
        }

        Adoption response = repository.save(existingAdoption);

        return mapper.toDTO(response);
    }

    public List<AdoptionResponseDTO> getDaySchedule(LocalDate date){
        return ((AdoptionRepository) repository).findByDate(date)
                .stream()
                .filter(e -> (e.getStatus() != cancelled)) //Do not show in schedule cancelled visits
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

}
