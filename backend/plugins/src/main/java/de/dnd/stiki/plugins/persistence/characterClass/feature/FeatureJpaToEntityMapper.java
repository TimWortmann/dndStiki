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
        entity.setName(jpa.getTrait().getName());
        entity.setText(jpa.getTrait().getText());
        entity.setOptional(jpa.isOptional());

        return entity;
    }
}
