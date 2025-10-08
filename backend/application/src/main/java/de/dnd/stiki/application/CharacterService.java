package de.dnd.stiki.application;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.CharacterDtoToEntityMapper;
import de.dnd.stiki.adapters.character.CharacterEntityToDtoMapper;
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

    public List<CharacterDto> getAll() {
        return entityToDtoMapper.mapEntitiesToDtos(repository.getAll());
    }

    public CharacterDto create(String name) {
        return entityToDtoMapper.mapEntityToDto(repository.create(name));
    }

    public CharacterDto save(CharacterDto characterDto) {

        CharacterEntity entity = repository.save(dtoToEntityMapper.mapDtoToEntity(characterDto));
        return entityToDtoMapper.mapEntityToDto(entity);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
