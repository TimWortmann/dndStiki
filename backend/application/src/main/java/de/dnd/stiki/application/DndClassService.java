package de.dnd.stiki.application;

import de.dnd.stiki.adapters.dndClass.DndClassDto;
import de.dnd.stiki.adapters.dndClass.DndClassEntityToDtoMapper;
import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.DndClassRepository;
import org.springframework.stereotype.Service;

@Service
public class DndClassService extends AbstractService<
        DndClassEntity,
        DndClassDto,
        DndClassRepository,
        DndClassEntityToDtoMapper> {
}
