package de.dnd.stiki.application;

import de.dnd.stiki.adapters.characterClass.CharacterClassDto;
import de.dnd.stiki.adapters.characterClass.CharacterClassEntityToDtoMapper;
import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import de.dnd.stiki.domain.characterClass.CharacterClassRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterClassService extends AbstractService<
        CharacterClassEntity,
        CharacterClassDto,
        CharacterClassRepository,
        CharacterClassEntityToDtoMapper> {
}
