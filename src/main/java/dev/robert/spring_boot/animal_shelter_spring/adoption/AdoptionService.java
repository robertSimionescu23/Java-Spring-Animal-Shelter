package dev.robert.spring_boot.animal_shelter_spring.adoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.animal.Animal;
import dev.robert.spring_boot.animal_shelter_spring.animal.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.base.classes.ServiceBase;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.exceptions.ResourceNotFoundException;

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

        // Fetch the Animal entity referenced by animalId in the DTO
        Animal linkedAnimal = animalRepository.findById(dto.getAnimalId())
            .orElseThrow(() -> new ResourceNotFoundException("Animal not found with id " + dto.getAnimalId()));

        // Set the Animal reference
        adoption.setAnimal(linkedAnimal);

        // Save Adoption entity
        Adoption savedAdoption = getRepository().save(adoption);

        // Set the adoption entity on referenced animal
        linkedAnimal.setAdoption(savedAdoption);
        animalRepository.save(linkedAnimal);

        return getMapper().toDTO(savedAdoption);
    }

}
