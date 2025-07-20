package dev.robert.spring_boot.animal_shelter_spring.service.base;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.robert.spring_boot.animal_shelter_spring.mapper.base.MapperInterface;

public abstract class ServiceBase<ENTITY, REQDTO, RESDTO, ID> implements ServiceInterface<ENTITY, REQDTO, RESDTO, ID> {
    protected abstract JpaRepository<ENTITY, ID> getRepository();

    protected abstract MapperInterface<REQDTO, RESDTO, ENTITY> getMapper();



    @Override
    public RESDTO findById(ID id) {
        Optional<ENTITY> entity = getRepository().findById(id);
        return entity.map(e -> getMapper().toDTO(e)).orElse(null);
    }

    @Override
    public List<RESDTO> findAll() {
        return getRepository().findAll()
                .stream()
                .map(e -> getMapper().toDTO(e))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(ID id){
        getRepository().deleteById(id);
    }

    @Override
    public RESDTO save(REQDTO dto){
        ENTITY entity = getMapper().toEntity(dto);
        ENTITY savedentity = getRepository().save(entity);
        return getMapper().toDTO(savedentity);
    }

    @Override
    public boolean existsById(ID id){
        return getRepository().existsById(id);
    }
}
