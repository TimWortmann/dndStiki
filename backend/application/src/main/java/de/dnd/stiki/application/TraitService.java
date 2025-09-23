package de.dnd.stiki.application;

import de.dnd.stiki.adapters.trait.TraitDto;
import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.trait.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraitService {

    @Autowired
    private TraitRepository repository;

    @Autowired
    private TraitEntityToDtoMapper entityToDtoMapper;

    public List<TraitDto> getAllTraits() {

        return entityToDtoMapper.mapEntitiesToDtos(repository.getAllTraits());
    }
}
