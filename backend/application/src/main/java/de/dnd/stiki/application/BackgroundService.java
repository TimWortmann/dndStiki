package de.dnd.stiki.application;

import de.dnd.stiki.adapters.background.BackgroundDto;
import de.dnd.stiki.adapters.background.BackgroundEntityToDtoMapper;
import de.dnd.stiki.domain.background.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundService {

    @Autowired
    private BackgroundRepository repository;

    @Autowired
    private BackgroundEntityToDtoMapper entityToDtoMapper;

    public List<BackgroundDto> getAllBackgrounds() {

        return entityToDtoMapper.mapEntitiesToDtos(repository.getAll());
    }
}
