package de.dnd.stiki.plugins.persistence.feat.modifier;

import de.dnd.stiki.domain.feat.ModifierEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class ModifierEntityToJpaMapper extends AbstractEntityToJpaMapper<ModifierEntity, ModifierJpa> {

    @Override
    public ModifierJpa mapEntityToJpa(ModifierEntity entity) {
        ModifierJpa jpa = new ModifierJpa();
        jpa.setCategory(entity.getCategory());
        jpa.setValue(entity.getValue());
        return jpa;
    }
}
