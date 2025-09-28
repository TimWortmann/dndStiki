package de.dnd.stiki.application;

import de.dnd.stiki.adapters.race.RaceDto;
import de.dnd.stiki.adapters.race.RaceEntityToDtoMapper;
import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import org.springframework.stereotype.Service;

@Service
public class RaceService extends AbstractService<
        RaceEntity,
        RaceDto,
        RaceRepository,
        RaceEntityToDtoMapper> {
}
