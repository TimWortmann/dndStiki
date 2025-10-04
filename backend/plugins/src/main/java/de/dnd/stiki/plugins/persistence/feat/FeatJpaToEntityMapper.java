package de.dnd.stiki.plugins.persistence.feat;

import de.dnd.stiki.domain.feat.FeatEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.feat.modifier.ModifierJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatJpaToEntityMapper extends AbstractJpaToEntityMapper<FeatJpa, FeatEntity> {

    @Autowired
    private ModifierJpaToEntityMapper modifierJpaToEntityMapper;

    @Override
    public FeatEntity mapJpaToEntity(FeatJpa jpa) {
        FeatEntity entity = new FeatEntity();
        entity.setName(jpa.getName());
        entity.setPrerequisites(getListFromString(jpa.getPrerequisites()));
        entity.setText(jpa.getText());
        entity.setModifiers(modifierJpaToEntityMapper.mapJpasToEntities(jpa.getModifiers()));
        return entity;
    }
}
