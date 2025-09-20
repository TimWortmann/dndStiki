package de.dnd.stiki.adapters.trait;

import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TraitEntityToDtoMapper {

    public List<TraitDto> mapEntitiesToDtos(List<TraitEntity> entities) {
        List<TraitDto> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    public TraitDto mapEntityToDto(TraitEntity entity) {
        TraitDto dto = new TraitDto();
        dto.setName(entity.getName());
        dto.setText(entity.getText());
        return dto;
    }
}
