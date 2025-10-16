package de.dnd.stiki.application;

import de.dnd.stiki.adapters.background.BackgroundDto;
import de.dnd.stiki.adapters.background.BackgroundEntityToDtoMapper;
import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import org.springframework.stereotype.Service;

@Service
public class BackgroundService extends AbstractService<
        BackgroundEntity,
        BackgroundDto,
        BackgroundRepository,
        BackgroundEntityToDtoMapper> {

    public BackgroundDto getByName(String name) {
        return entityToDtoMapper.mapEntityToDto(repository.getByName(name));
    }
}
