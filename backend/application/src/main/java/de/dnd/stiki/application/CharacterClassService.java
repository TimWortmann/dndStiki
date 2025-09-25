package de.dnd.stiki.application;

import de.dnd.stiki.adapters.characterClass.CharacterClassDto;
import de.dnd.stiki.adapters.characterClass.CharacterClassEntityToDtoMapper;
import de.dnd.stiki.domain.characterClass.feature.CharacterClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterClassService {

    @Autowired
    private CharacterClassRepository repository;

    @Autowired
    private CharacterClassEntityToDtoMapper entityToDtoMapper;

    public List<CharacterClassDto> getAllCharacterClasses() {

        return entityToDtoMapper.mapEntitiesToDtos(repository.getAllCharacterClasses());
    }
}
