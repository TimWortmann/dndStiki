package de.dnd.stiki.adapters.feat;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.feat.FeatEntity;
import org.springframework.stereotype.Component;

@Component
public class FeatEntityToDtoMapper extends AbstractEntityToDtoMapper<FeatEntity, FeatDto> {

    @Override
    public FeatDto mapEntityToDto(FeatEntity entity) {
        FeatDto dto = new FeatDto();
        dto.setName(entity.getName());
        dto.setPrerequisites(entity.getPrerequisites());
        dto.setText(entity.getText());
        return dto;
    }
}
