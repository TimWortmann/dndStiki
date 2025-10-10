package de.dnd.stiki.plugins.persistence.basic.trait;

import de.dnd.stiki.domain.trait.TraitEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class TraitJpaToEntityMapper extends AbstractJpaToEntityMapper<TraitJpa, TraitEntity> {

    @Override
    public TraitEntity mapJpaToEntity(TraitJpa jpa) {
        TraitEntity entity = new TraitEntity();
        entity.setName(jpa.getName());
        entity.setText(jpa.getText());
        return entity;
    }
}
