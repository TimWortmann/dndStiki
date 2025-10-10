package de.dnd.stiki.adapters.dndClass.feature;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import org.springframework.stereotype.Component;

@Component
public class FeatureEntityToDtoMapper extends AbstractEntityToDtoMapper<FeatureEntity, FeatureDto> {

    @Override
    public FeatureDto mapEntityToDto(FeatureEntity entity) {
        FeatureDto dto = new FeatureDto();
        dto.setName(entity.getName());
        dto.setText(entity.getText());
        dto.setOptional(entity.isOptional());
        return dto;
    }
}
