package de.dnd.stiki.plugins.persistence.feat;

import de.dnd.stiki.domain.feat.FeatEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.feat.modifier.ModifierEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatEntityToJpaMapper extends AbstractEntityToJpaMapper<FeatEntity, FeatJpa> {

    @Autowired
    private ModifierEntityToJpaMapper modifierEntityToJpaMapper;

    @Override
    public FeatJpa mapEntityToJpa(FeatEntity entity) {
        FeatJpa jpa = new FeatJpa();
        jpa.setName(entity.getName());
        jpa.setPrerequisites(getStringFromList(entity.getPrerequisites()));
        jpa.setText(entity.getText());
        jpa.setModifiers(modifierEntityToJpaMapper.mapEntitiesToJpa(entity.getModifiers()));
        return jpa;
    }
}
