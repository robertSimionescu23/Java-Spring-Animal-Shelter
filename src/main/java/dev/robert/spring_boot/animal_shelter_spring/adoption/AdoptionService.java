package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;

import java.util.List;

import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.CANCELLED;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.PENDING;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.DONE;

@Service
public class AdoptionService extends ServiceBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long,
    AdoptionRepository
>{

    @Autowired private AnimalRepository animalRepository;

    public AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper) {
        super(adoptionRepository, adoptionMapper);
    }

    @Override
    public AdoptionResponseDTO save(AdoptionRequestDTO dto){
        Adoption adoption = mapper.toEntity(dto);

        // Fetch the Animal entity referenced by animalId in the DTO
        Animal linkedAnimal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id " + dto.getAnimalId()));

        List<Adoption> existingAdoptionsList = repository.findByAnimalId(linkedAnimal.getId());

        if(dto.getStatus() == null)
            dto.setStatus(PENDING);

        if(dto.getStatus().equals(CANCELLED) || dto.getStatus().equals(DONE)){
            throw new IllegalStateException("Adoptions entities may only be created with pending status. The state can be altered later on.");
        }

        // if(!checkScheduleOverlap(dto))
        //     throw new IllegalStateException("The requested time slot is already filled.");

        if(dto.getStatus().equals(PENDING)){
            for(Adoption iterAdoption : existingAdoptionsList){
                if(iterAdoption.getStatus().equals(PENDING) || iterAdoption.getStatus().equals(DONE)){
                    //There cannot be 2 pending adoptions at a time or a pending adoption after a adoption already went through.
                    throw new IllegalStateException("The adoption status is already pending or done.");
                }
            }
        }
        // Set the Animal reference
        adoption.setAnimal(linkedAnimal);

        // Save Adoption entity
        Adoption savedAdoption = repository.save(adoption);

        // Set the adoption entity on referenced animal
        linkedAnimal.setAdoptionStatus(PENDING);
        animalRepository.save(linkedAnimal);

        return mapper.toDTO(savedAdoption);
    }

    @Override
    public AdoptionResponseDTO patch(Long id, String field, AdoptionRequestDTO req){

        Adoption existingAdoption = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Adoption with id " + id + " cannot be found."));

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

                Animal linkedAnimal = existingAdoption.getAnimal();
                List<Adoption> existingAdoptionsList = repository.findByAnimalId(linkedAnimal.getId());

                //Make sure that there are no other pending adoptions
                if(req.getStatus().equals(PENDING) || req.getStatus().equals(DONE)) {
                    for (Adoption iterAdoption : existingAdoptionsList) {
                        //Check all other elements
                        if(iterAdoption.getId().equals(id))
                            continue;
                        if (iterAdoption.getStatus().equals(PENDING) || iterAdoption.getStatus().equals(DONE)) {
                            throw new ResourceNotFoundException("There can only be one adoption pending or done, per animal.");
                        }
                    }
                }
                linkedAnimal.setAdoptionStatus(req.getStatus());
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

            default -> throw new ResourceNotFoundException("Field to patch cannot be found.");
        }

        Adoption response = repository.save(existingAdoption);

        return mapper.toDTO(response);
    }

}
