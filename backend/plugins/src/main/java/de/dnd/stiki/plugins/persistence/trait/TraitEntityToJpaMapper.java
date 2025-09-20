package de.dnd.stiki.plugins.persistence.trait;

import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.stereotype.Component;

@Component
public class TraitEntityToJpaMapper {

    public TraitJpa mapEntityToJpa(TraitEntity entity) {
        TraitJpa jpa = new TraitJpa();

        jpa.setId(entity.getId());
        jpa.setName(entity.getName());
        jpa.setText(entity.getText());

        return jpa;
    }
}
