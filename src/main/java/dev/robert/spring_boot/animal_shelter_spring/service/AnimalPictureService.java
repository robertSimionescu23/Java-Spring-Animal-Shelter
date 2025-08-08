package dev.robert.spring_boot.animal_shelter_spring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import dev.robert.spring_boot.animal_shelter_spring.dto.AnimalPictureDTO;
import dev.robert.spring_boot.animal_shelter_spring.mapper.AnimalPictureMapper;
import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.model.AnimalPicture;
import dev.robert.spring_boot.animal_shelter_spring.repository.AnimalPictureRepository;
import dev.robert.spring_boot.animal_shelter_spring.service.base.ServiceBase;

@Service
public class AnimalPictureService extends ServiceBase<
    AnimalPicture,
    AnimalPictureDTO,
    AnimalPictureDTO,
    Long
> {

    private final AnimalPictureMapper mapper;
    private final AnimalPictureRepository repository;

    public AnimalPictureService(AnimalPictureMapper mapper, AnimalPictureRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected MapperInterface<AnimalPictureDTO, AnimalPictureDTO, AnimalPicture> getMapper() {
        return mapper;
    }


    @Override
    protected JpaRepository<AnimalPicture, Long> getRepository() {
        return repository;
    }

}
