package de.dnd.stiki.plugins.persistence.characterClass.feature;

import de.dnd.stiki.domain.characterClass.feature.FeatureEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class FeatureJpaToEntityMapper extends AbstractJpaToEntityMapper<FeatureJpa, FeatureEntity> {

    @Override
    public FeatureEntity mapJpaToEntity(FeatureJpa jpa) {

        FeatureEntity entity = new FeatureEntity();
        entity.setId(jpa.getId());
        entity.setOptional(jpa.isOptional());
        entity.setText(jpa.getTrait().getText());

        return entity;
    }
}
