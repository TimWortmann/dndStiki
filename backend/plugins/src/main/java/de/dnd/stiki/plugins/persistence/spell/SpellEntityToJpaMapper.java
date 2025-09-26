package de.dnd.stiki.plugins.persistence.spell;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class SpellEntityToJpaMapper extends AbstractEntityToJpaMapper<SpellEntity, SpellJpa> {

    @Override
    public SpellJpa mapEntityToJpa(SpellEntity entity) {

        SpellJpa jpa = new SpellJpa();
        jpa.setName(entity.getName());
        jpa.setLevel(entity.getLevel());
        jpa.setSchool(entity.getSchool());
        jpa.setTime(entity.getTime());
        jpa.setRange(entity.getRange());
        jpa.setComponents(getStringFromList(entity.getComponents()));
        jpa.setDuration(entity.getDuration());
        jpa.setText(entity.getText());
        jpa.setRoll(entity.getRoll());
        jpa.setClasses(getStringFromList(entity.getClasses()));

        return jpa;
    }
}
