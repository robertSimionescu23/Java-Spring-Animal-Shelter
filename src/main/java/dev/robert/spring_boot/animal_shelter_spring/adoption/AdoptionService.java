package dev.robert.spring_boot.animal_shelter_spring.adoption;

import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalRequestDTO;
import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalResponseDTO;
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

@Service
public class AdoptionService extends ServiceBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long
>{

    @Autowired private AnimalRepository animalRepository;
    private final AdoptionRepository adoptionRepository;
    private final AdoptionMapper adoptionMapper;


    public AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper) {
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
    }

    @Override
    protected MapperInterface<AdoptionRequestDTO, AdoptionResponseDTO, Adoption> getMapper(){
        return adoptionMapper;
    }

    @Override
    protected JpaRepository<Adoption, Long> getRepository(){
        return adoptionRepository;
    }

    @Override
    public AdoptionResponseDTO save(AdoptionRequestDTO dto){
        Adoption adoption = getMapper().toEntity(dto);
        List<Adoption> existingAdoptions;

        // Fetch the Animal entity referenced by animalId in the DTO
        Animal linkedAnimal = animalRepository.findById(dto.getAnimalId())
            .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id " + dto.getAnimalId()));

        existingAdoptions = linkedAnimal.getAdoptions();

        if(!existingAdoptions.stream().allMatch(checkAdoption -> checkAdoption.getStatus() == cancelled)){
            throw new RuntimeException("There cannot be more than 1 pending adoption associated with an animal, at any time.");
        }

        // Set the Animal reference
        adoption.setAnimal(linkedAnimal);

        // Save Adoption entity
        Adoption savedAdoption = getRepository().save(adoption);

        // Set the adoption entity on referenced animal
        linkedAnimal.getAdoptions().add(savedAdoption); //TODO: check that it is not possible to have 2 pending or done adoptions at the same time.
        animalRepository.save(linkedAnimal);

        return getMapper().toDTO(savedAdoption);
    }

    public AdoptionResponseDTO patch(Long id, String field, AdoptionRequestDTO req){

        Adoption existingAdoption = getRepository().findById(id)
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

        Adoption response = getRepository().save(existingAdoption);

        return getMapper().toDTO(response);
    }

}
