package de.dnd.stiki.plugins.persistence.spell;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class SpellJpaToEntityMapper extends AbstractJpaToEntityMapper<SpellJpa, SpellEntity> {

    @Override
    public SpellEntity mapJpaToEntity(SpellJpa jpa) {
        SpellEntity entity = new SpellEntity();
        entity.setName(jpa.getName());
        entity.setLevel(jpa.getLevel());
        entity.setSchool(jpa.getSchool());
        entity.setTime(jpa.getTime());
        entity.setRange(jpa.getRange());
        entity.setComponents(getListFromString(jpa.getComponents()));
        entity.setDuration(jpa.getDuration());
        entity.setText(jpa.getText());
        entity.setRoll(jpa.getRoll());
        entity.setClasses(getListFromString(jpa.getClasses()));
        entity.setRitual(jpa.isRitual());
        return  entity;
    }
}
