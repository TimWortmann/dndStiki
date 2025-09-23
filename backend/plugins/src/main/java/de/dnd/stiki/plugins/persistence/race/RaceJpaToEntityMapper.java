package de.dnd.stiki.plugins.persistence.race;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.trait.TraitJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceJpaToEntityMapper extends AbstractJpaToEntityMapper<RaceJpa, RaceEntity> {

    @Autowired
    private TraitJpaToEntityMapper traitJpaToEntityMapper;

    @Override
    public RaceEntity mapJpaToEntity(RaceJpa jpa) {

        RaceEntity entity = new RaceEntity();
        entity.setName(jpa.getName());
        entity.setSize(jpa.getSize());
        entity.setSpeed(jpa.getSpeed());
        entity.setAbility(jpa.getAbility());

        if (jpa.getSpellAbility() != null) {
            entity.setSpellAbility(jpa.getSpellAbility().getName());
        }

        entity.setTraits(traitJpaToEntityMapper.mapJpasToEntities(jpa.getTraits()));
        return entity;
    }
}
