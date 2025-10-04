package de.dnd.stiki.plugins.persistence.feat.modifier;

import de.dnd.stiki.domain.feat.ModifierEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class ModifierJpaToEntityMapper extends AbstractJpaToEntityMapper<ModifierJpa, ModifierEntity> {

    @Override
    public ModifierEntity mapJpaToEntity(ModifierJpa jpa) {

        ModifierEntity entity = new ModifierEntity();
        entity.setCategory(jpa.getCategory());
        entity.setValue(jpa.getValue());
        return entity;
    }
}
