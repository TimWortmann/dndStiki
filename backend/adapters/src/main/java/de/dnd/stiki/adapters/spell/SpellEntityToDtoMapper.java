package de.dnd.stiki.adapters.spell;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.spell.SpellEntity;
import org.springframework.stereotype.Component;

@Component
public class SpellEntityToDtoMapper extends AbstractEntityToDtoMapper<SpellEntity, SpellDto> {

    @Override
    public SpellDto mapEntityToDto(SpellEntity entity) {

        SpellDto dto = new SpellDto();
        dto.setName(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setSchool(entity.getSchool());
        dto.setTime(entity.getTime());
        dto.setRange(entity.getRange());
        dto.setComponents(entity.getComponents());
        dto.setDuration(entity.getDuration());
        dto.setText(entity.getText());
        dto.setRoll(entity.getRoll());

        return dto;
    }
}
