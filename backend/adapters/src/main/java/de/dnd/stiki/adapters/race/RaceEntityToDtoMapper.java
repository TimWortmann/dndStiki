package de.dnd.stiki.adapters.race;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.race.RaceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceEntityToDtoMapper extends AbstractEntityToDtoMapper<RaceEntity, RaceDto> {

    @Autowired
    private TraitEntityToDtoMapper traitMapper;

    @Override
    public RaceDto mapEntityToDto(RaceEntity entity) {

        RaceDto dto = new RaceDto();
        dto.setName(entity.getName());
        dto.setSize(entity.getSize());
        dto.setSpeed(entity.getSpeed());
        dto.setAbility(entity.getAbility());
        dto.setSpellAbility(entity.getSpellAbility());
        dto.setTraits(traitMapper.mapEntitiesToDtos(entity.getTraits()));

        return dto;
    }
}
