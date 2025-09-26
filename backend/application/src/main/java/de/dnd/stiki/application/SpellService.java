package de.dnd.stiki.application;

import de.dnd.stiki.adapters.spell.SpellDto;
import de.dnd.stiki.adapters.spell.SpellEntityToDtoMapper;
import de.dnd.stiki.domain.spell.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpellService {

    @Autowired
    private SpellRepository repository;

    @Autowired
    private SpellEntityToDtoMapper entityToDtoMapper;

    public List<SpellDto> getAllSpells() {

        return entityToDtoMapper.mapEntitiesToDtos(repository.getAllSpells());
    }
}
