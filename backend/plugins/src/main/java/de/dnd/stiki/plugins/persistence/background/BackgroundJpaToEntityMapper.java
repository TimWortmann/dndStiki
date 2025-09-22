package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.trait.TraitJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackgroundJpaToEntityMapper extends AbstractJpaToEntityMapper<BackgroundJpa, BackgroundEntity> {

    @Autowired
    private TraitJpaToEntityMapper traitJpaToEntityMapper;

    @Override
    public BackgroundEntity mapJpaToEntity(BackgroundJpa jpa) {
        BackgroundEntity entity = new BackgroundEntity();

        entity.setName(jpa.getName());

        if (jpa.getProficiencies() != null) {
            entity.setProficiencies(jpa.getProficiencies().stream().map(SkillJpa::getName).toList());
        }

        entity.setTraits(traitJpaToEntityMapper.mapJpasToEntities(jpa.getTraits()));
        return entity;
    }
}
