package de.dnd.stiki.application;

import de.dnd.stiki.adapters.race.RaceDto;
import de.dnd.stiki.adapters.race.RaceEntityToDtoMapper;
import de.dnd.stiki.domain.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceService {

    @Autowired
    private RaceRepository repository;

    @Autowired
    private RaceEntityToDtoMapper entityToDtoMapper;

    public List<RaceDto> getAllRaces() {
        return entityToDtoMapper.mapEntitiesToDtos(repository.getAllRaces());
    }
}
