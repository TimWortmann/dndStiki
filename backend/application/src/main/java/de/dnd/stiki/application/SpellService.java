package de.dnd.stiki.application;

import de.dnd.stiki.adapters.spell.SpellDto;
import de.dnd.stiki.adapters.spell.SpellEntityToDtoMapper;
import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.domain.spell.SpellRepository;
import org.springframework.stereotype.Service;

@Service
public class SpellService extends AbstractService<
        SpellEntity,
        SpellDto,
        SpellRepository,
        SpellEntityToDtoMapper> {
}
