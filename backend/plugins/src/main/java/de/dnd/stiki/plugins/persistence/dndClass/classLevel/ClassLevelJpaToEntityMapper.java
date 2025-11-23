package de.dnd.stiki.plugins.persistence.dndClass.classLevel;

import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.dndClass.counter.CounterJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.dndClass.feature.FeatureJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassLevelJpaToEntityMapper extends AbstractJpaToEntityMapper<ClassLevelJpa, ClassLevelEntity> {

    @Autowired
    private FeatureJpaToEntityMapper featureJpaToEntityMapper;

    @Autowired
    private CounterJpaToEntityMapper counterJpaToEntityMapper;

    @Override
    public ClassLevelEntity mapJpaToEntity(ClassLevelJpa jpa) {

        ClassLevelEntity entity = new ClassLevelEntity();
        entity.setId(jpa.getId());
        entity.setLevel(jpa.getLevel());
        entity.setScoreImprovement(jpa.isScoreImprovement());
        entity.setSpellSlots(getIntegerListFromString(jpa.getSpellSlots()));
        entity.setFeatures(featureJpaToEntityMapper.mapJpasToEntities(jpa.getFeatures()));
        entity.setCounters(counterJpaToEntityMapper.mapJpasToEntities(jpa.getCounters()));

        return entity;
    }
}
