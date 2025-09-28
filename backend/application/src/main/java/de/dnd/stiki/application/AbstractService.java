package de.dnd.stiki.application;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<
        ENTITY,
        DTO,
        REPOSITORY extends AbstractRepository<ENTITY>,
        ENTITY_TO_DTO_MAPPER extends AbstractEntityToDtoMapper<ENTITY, DTO>
        > {

    @Autowired
    private REPOSITORY repository;

    @Autowired
    private ENTITY_TO_DTO_MAPPER entityToDtoMapper;

    public List<DTO> getAll() {
        return entityToDtoMapper.mapEntitiesToDtos(repository.getAll());
    }
}
