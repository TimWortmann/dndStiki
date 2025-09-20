package de.dnd.stiki.adapters.trait;

import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Component;

@Component
public class TraitDtoToEntityMapper {

    public TraitEntity mapLightDtoToEntity(TraitLightDto dto) {
        TraitEntity entity = new TraitEntity();
        entity.setName(dto.getName());
        entity.setText(dto.getText());

        return entity;
    }

    public TraitEntity mapDtoToEntity(TraitDto dto) {
        TraitEntity entity = mapLightDtoToEntity(dto);
        entity.setId(dto.getId());

        return entity;
    }
}
