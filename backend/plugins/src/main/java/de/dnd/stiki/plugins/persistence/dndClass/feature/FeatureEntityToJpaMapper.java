package de.dnd.stiki.plugins.persistence.dndClass.feature;

import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpa;
import org.springframework.stereotype.Component;

@Component
public class FeatureEntityToJpaMapper extends AbstractEntityToJpaMapper<FeatureEntity, FeatureJpa> {

    @Override
    public FeatureJpa mapEntityToJpa(FeatureEntity entity) {
        FeatureJpa jpa = new FeatureJpa();
        jpa.setOptional(entity.isOptional());
        TraitJpa traitJpa = new TraitJpa();
        traitJpa.setName(entity.getName());
        traitJpa.setText(entity.getText());
        jpa.setTrait(traitJpa);
        return jpa;
    }
}
