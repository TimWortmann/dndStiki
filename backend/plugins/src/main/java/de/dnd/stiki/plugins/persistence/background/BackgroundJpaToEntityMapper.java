package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class BackgroundJpaToEntityMapper extends AbstractJpaToEntityMapper<BackgroundJpa, BackgroundEntity> {

    @Override
    public BackgroundEntity mapJpaToEntity(BackgroundJpa jpa) {
        BackgroundEntity entity = new BackgroundEntity();

        entity.setName(jpa.getName());
        return entity;
    }
}
