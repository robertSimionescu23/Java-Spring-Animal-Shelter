package dev.robert.spring_boot.animal_shelter_spring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionRequestDTO;
import dev.robert.spring_boot.animal_shelter_spring.dto.AdoptionResponseDTO;
import dev.robert.spring_boot.animal_shelter_spring.mapper.AdoptionMapper;
import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.model.Adoption;
import dev.robert.spring_boot.animal_shelter_spring.repository.AdoptionRepository;
import dev.robert.spring_boot.animal_shelter_spring.service.base.ServiceBase;

@Service
public class AdoptionService extends ServiceBase<Adoption, AdoptionRequestDTO, AdoptionResponseDTO, Integer>{

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
    protected JpaRepository<Adoption, Integer> getRepository(){
        return adoptionRepository;
    }

}
