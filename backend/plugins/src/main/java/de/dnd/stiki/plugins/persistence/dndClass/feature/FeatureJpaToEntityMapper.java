package de.dnd.stiki.plugins.persistence.dndClass.feature;

import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class FeatureJpaToEntityMapper extends AbstractJpaToEntityMapper<FeatureJpa, FeatureEntity> {

    @Override
    public FeatureEntity mapJpaToEntity(FeatureJpa jpa) {
        FeatureEntity entity = new FeatureEntity();
        entity.setName(jpa.getTrait().getName());
        entity.setText(jpa.getTrait().getText());
        entity.setOptional(jpa.isOptional());
        return entity;
    }
}
