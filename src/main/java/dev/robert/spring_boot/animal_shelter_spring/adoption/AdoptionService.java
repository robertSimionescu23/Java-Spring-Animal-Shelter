package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;

import java.util.List;

import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.cancelled;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.pending;
import static dev.robert.spring_boot.animal_shelter_spring.adoption.AdoptionStatusEnum.done;

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

    public AdoptionResponseDTO post(AdoptionRequestDTO dto){
        Adoption adoption = mapper.toEntity(dto);

        // Fetch the Animal entity referenced by animalId in the DTO
        Animal linkedAnimal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id " + dto.getAnimalId()));

        List<Adoption> existingAdoptions = linkedAnimal.getAdoptions();

        for (Adoption iterAdoption : existingAdoptions) {
            if (iterAdoption.getStatus().equals(pending) || iterAdoption.getStatus().equals(done)) {
                throw new ResourceNotFoundException("There can only be one adoption pending or done, per animal.");
            }
        }

        if(!dto.getStatus().equals(pending)){
            throw new RuntimeException("Adoptions cannot be created in any other state than pending. They need to be changed later on.");
        }

        // Set the Animal reference
        adoption.setAnimal(linkedAnimal);

        // Save Adoption entity
        Adoption savedAdoption = repository.save(adoption);

        // Set the adoption entity on referenced animal
        linkedAnimal.getAdoptions().add(savedAdoption); //TODO: check that it is not possible to have 2 pending or done adoptions at the same time.
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
            default -> throw new ResourceNotFoundException("Field to patch cannot be found.");
        }

        Adoption response = repository.save(existingAdoption);

        return mapper.toDTO(response);
    }

}
