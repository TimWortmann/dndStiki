package de.dnd.stiki.application;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.CharacterDtoToEntityMapper;
import de.dnd.stiki.adapters.character.CharacterEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterEntityToDtoMapper entityToDtoMapper;

    @Autowired
    private CharacterDtoToEntityMapper dtoToEntityMapper;

    @Autowired
    private CharacterCreationDtoToEntityMapper creationDtoToEntityMapper;

    public List<CharacterDto> getAll() {
        return entityToDtoMapper.mapEntitiesToDtos(repository.getAll());
    }

    public CharacterDto get(Long id) {
        return entityToDtoMapper.mapEntityToDto(repository.get(id));
    }

    public CharacterDto create(CharacterCreationDto creationDto) {
        CharacterEntity entity = repository.save(creationDtoToEntityMapper.mapDtoToEntity(creationDto));
        return entityToDtoMapper.mapEntityToDto(entity);
    }

    public CharacterDto save(CharacterDto characterDto) {

        CharacterEntity entity = repository.save(dtoToEntityMapper.mapDtoToEntity(characterDto));
        return entityToDtoMapper.mapEntityToDto(entity);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
