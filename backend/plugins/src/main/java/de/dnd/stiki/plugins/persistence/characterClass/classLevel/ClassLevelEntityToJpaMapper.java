package de.dnd.stiki.plugins.persistence.characterClass.classLevel;

import de.dnd.stiki.domain.characterClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.characterClass.counter.CounterEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.characterClass.feature.FeatureEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassLevelEntityToJpaMapper extends AbstractEntityToJpaMapper<ClassLevelEntity, ClassLevelJpa> {

    @Autowired
    FeatureEntityToJpaMapper featureEntityToJpaMapper;

    @Autowired
    CounterEntityToJpaMapper counterEntityToJpaMapper;

    @Override
    public ClassLevelJpa mapEntityToJpa(ClassLevelEntity entity) {

        ClassLevelJpa jpa = new ClassLevelJpa();
        jpa.setId(entity.getId());
        jpa.setLevel(entity.getLevel());
        jpa.setScoreImprovement(entity.hasScoreImprovement());
        jpa.setSpellSlots(entity.getSpellSlots());
        jpa.setFeatures(featureEntityToJpaMapper.mapEntitiesToJpa(entity.getFeatures()));
        jpa.setCounters(counterEntityToJpaMapper.mapEntitiesToJpa(entity.getCounters()));

        return jpa;
    }
}
