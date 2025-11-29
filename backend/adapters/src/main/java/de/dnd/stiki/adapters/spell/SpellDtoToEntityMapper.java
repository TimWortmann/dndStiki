package de.dnd.stiki.adapters.spell;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.spell.SpellEntity;
import org.springframework.stereotype.Component;

@Component
public class SpellDtoToEntityMapper extends AbstractDtoToEntityMapper<SpellDto, SpellEntity> {

    @Override
    public SpellEntity mapDtoToEntity(SpellDto dto) {
        SpellEntity entity = new SpellEntity();
        entity.setName(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setSchool(dto.getSchool());
        entity.setTime(dto.getTime());
        entity.setRange(dto.getRange());
        entity.setComponents(dto.getComponents());
        entity.setDuration(dto.getDuration());
        entity.setText(dto.getText());
        entity.setRoll(dto.getRoll());
        entity.setClasses(dto.getClasses());
        entity.setRitual(dto.isRitual());
        return entity;
    }
}
