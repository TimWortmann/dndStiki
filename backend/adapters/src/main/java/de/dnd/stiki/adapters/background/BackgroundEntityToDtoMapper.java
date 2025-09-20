package de.dnd.stiki.adapters.background;

import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.background.BackgroundEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BackgroundEntityToDtoMapper {

    @Autowired
    private TraitEntityToDtoMapper traitMapper;

    public List<BackgroundDto> mapEntitiesToDtos(List<BackgroundEntity> entities) {
        List<BackgroundDto> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    public BackgroundDto mapEntityToDto(BackgroundEntity entity) {

        BackgroundDto dto = new BackgroundDto();
        dto.setName(entity.getName());
        dto.setProficiencies(entity.getProficiencies());
        dto.setTraits(traitMapper.mapEntitiesToDtos(entity.getTraits()));
        return dto;
    }
}
