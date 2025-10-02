package dev.robert.spring_boot.animal_shelter_spring.base.classes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.MapperInterface;
import dev.robert.spring_boot.animal_shelter_spring.base.interfaces.ServiceInterface;

public abstract class ServiceBase<ENTITY, REQDTO, RESDTO, ID, REPO extends JpaRepository<ENTITY, ID>> implements
ServiceInterface<ENTITY, REQDTO, RESDTO, ID> {
    protected final REPO repository;

    protected final MapperInterface<REQDTO, RESDTO, ENTITY> mapper;

    protected ServiceBase(REPO repository,
                          MapperInterface<REQDTO, RESDTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RESDTO findById(ID id) {
        Optional<ENTITY> entity = repository.findById(id);
        return entity.map(e -> mapper.toDTO(e)).orElse(null);
    }

    @Override
    public List<RESDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(e -> mapper.toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(ID id){
        repository.deleteById(id);
    }

    @Override
    public RESDTO save(REQDTO dto){
        ENTITY entity = mapper.toEntity(dto);
        ENTITY savedentity = repository.save(entity);
        return mapper.toDTO(savedentity);
    }

    @Override
    public boolean existsById(ID id){
        return repository.existsById(id);
    }
}
