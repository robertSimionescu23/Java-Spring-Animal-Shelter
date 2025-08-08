package dev.robert.spring_boot.animal_shelter_spring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionRequestDTO;
import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionResponseDTO;
import dev.robert.spring_boot.animal_shelter_spring.mapper.AdoptionMapper;
import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.model.Adoption;
import dev.robert.spring_boot.animal_shelter_spring.model.Animal;
import dev.robert.spring_boot.animal_shelter_spring.repository.AdoptionRepository;
import dev.robert.spring_boot.animal_shelter_spring.repository.AnimalRepository;
import dev.robert.spring_boot.animal_shelter_spring.service.base.ServiceBase;

@Service
public class AdoptionService extends ServiceBase<
    Adoption,
    AdoptionRequestDTO,
    AdoptionResponseDTO,
    Long
>{

    private final AdoptionRepository adoptionRepository;
    private final AnimalRepository animalRepository;
    private final AdoptionMapper adoptionMapper;

    public AdoptionService(AdoptionRepository adoptionRepository, AdoptionMapper adoptionMapper, AnimalRepository animalRepository) {
        this.adoptionRepository = adoptionRepository;
        this.adoptionMapper = adoptionMapper;
        this.animalRepository  = animalRepository;
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
        Adoption savedAdoption = getRepository().save(adoption);

        //Get the animal linked to the adoption, so it's adoption_id can be updated
        Animal linkedAnimal = savedAdoption.getAnimal();
        linkedAnimal.setAdoption(savedAdoption);
        animalRepository.save(linkedAnimal);

        return getMapper().toDTO(savedAdoption);
    }

}
