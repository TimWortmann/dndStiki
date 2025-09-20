package de.dnd.stiki.adapters.trait;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Component;

@Component
public class TraitEntityToDtoMapper extends AbstractEntityToDtoMapper<TraitEntity, TraitDto> {

    @Override
    public TraitDto mapEntityToDto(TraitEntity entity) {
        TraitDto dto = new TraitDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setText(entity.getText());
        return dto;
    }
}
